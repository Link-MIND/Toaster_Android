package org.sopt.timer

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.toast.linkMindSnackBar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.mainfeature.R
import org.sopt.model.timer.Timer
import org.sopt.timer.databinding.FragmentTimerBinding
import org.sopt.timer.modifytimer.ModifyTimerBottomSheetFragment
import org.sopt.timer.settimer.SetTimerViewModel
import org.sopt.timer.settimer.TimerUiState
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.fragment.colorOf
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope
import org.sopt.ui.nav.DeepLinkUtil
import org.sopt.ui.view.UiState
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class TimerFragment : BindingFragment<FragmentTimerBinding>({ FragmentTimerBinding.inflate(it) }) {
  private val setTimerViewModel: SetTimerViewModel by activityViewModels()
  private val viewModel: TimerViewModel by activityViewModels()
  private lateinit var completeTimerAdapter: CompleteTimerAdapter
  private lateinit var waitTimerAdapter: WaitTimerAdapter
  private var isNotiPermissionAllowed = true

  private val notificationPermissionLauncher =
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
      isNotiPermissionAllowed = isGranted
      viewModel.setUiState(isGranted)
      if (!isGranted) {
        NotificationPermissionDialogFragment().show(parentFragmentManager, this.tag)
      }
    }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    requestNotificationPermission()
    setTimerViewModel.initSetTimer()
    viewModel.getFcmAllowed(isNotiPermissionAllowed)
    if (viewModel.uiState.value is TimerUiState.BothAllowed) viewModel.getTimerMain()
    collectDeleteState()
    initCompleteTimerAdapter()
    initWaitTimerAdapter()
    initPlusButtonClickListener()
    initSetTimerButtonClickListener()
  }

  override fun onResume() {
    super.onResume()
    initCheckNotificationPermission()
    collectUiState()
  }

  private fun initWaitTimerAdapter() {
    waitTimerAdapter =
      WaitTimerAdapter(
        { viewModel.patchAlarm(it.id) },
        {
          showModifyTimerBottomSheetFragment(it)
        },
        {
          navigateToDestination(
            "featureSaveLink://ClipLinkFragment?categoryId=${it.categoryId}",
          )
        },
      )
    binding.rvTimerWait.adapter = waitTimerAdapter
    waitTimerAdapter.submitList(viewModel.timerList.value?.second)
  }

  private fun showModifyTimerBottomSheetFragment(it: Timer) {
    ModifyTimerBottomSheetFragment.newInstance(it.id, { viewModel.deleteTimer(it.id) }) {
      findNavController().navigate(
        TimerFragmentDirections.actionNavigationTimerToNavigationTimePicker(
          argPatch = true,
          argTimerId = it.id,
          argCategoryName = it.comment!!,
          argRemindTime = it.remindTime,
          argRemindDates = it.remindDates!!,
        ),
      )
    }.show(parentFragmentManager, this.tag)
  }

  private fun initCompleteTimerAdapter() {
    completeTimerAdapter = CompleteTimerAdapter {
      navigateToDestination(
        "featureSaveLink://ClipLinkFragment?categoryId=${it.categoryId}",
      )
    }
    completeTimerAdapter.submitList(viewModel.timerList.value?.first)
    binding.rvTimerComplete.adapter = completeTimerAdapter
  }

  private fun collectUiState() {
    viewModel.uiState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is TimerUiState.BothAllowed -> {
          viewModel.createEnable.value = true
          handleBothAllowedState(state)
          if (state.data?.first.isNullOrEmpty() && state.data?.second.isNullOrEmpty()) {
            Log.e("타이머", "널")
            viewModel.getTimerMain()
          }
        }

        is TimerUiState.AppAllowed -> {
          viewModel.createEnable.value = false
          handleAppAllowedState()
        }

        is TimerUiState.NotAllowed -> {
          viewModel.createEnable.value = false
          handleNotAllowedState()
        }

        is TimerUiState.DeviceAllowed -> {
          viewModel.createEnable.value = false
          handleDeviceAllowedState()
        }

        is TimerUiState.Loading -> {
        }

        is TimerUiState.Empty -> {
        }
      }
    }.launchIn(viewLifeCycleScope)
  }

  private fun handleDeviceAllowedState() {
    with(binding) {
      btnTimerSetEnable.isGone = true
      btnTimerSetDisable.isVisible = true
      clTimerPermissionOff.isVisible = true
      svTimerExist.isGone = true
      clTimer.isVisible = true
      clTimerNotiPermissionOff.isGone = true
    }
  }

  private fun handleNotAllowedState() {
    with(binding) {
      clTimerPermissionOff.isVisible = true
      svTimerExist.isGone = true
      clTimer.isGone = true
      clTimerNotiPermissionOff.isVisible = true
    }
  }

  private fun handleAppAllowedState() {
    with(binding) {
      clTimerPermissionOff.isGone = true
      svTimerExist.isGone = true
      clTimer.isGone = true
      clTimerNotiPermissionOff.isVisible = true
    }
  }

  private fun handleBothAllowedState(state: TimerUiState.BothAllowed<Pair<List<Timer>, List<Timer>>?>) {
    with(binding) {
      clTimerPermissionOff.isGone = true
      clTimerNotiPermissionOff.isGone = true
    }
    state.data?.let {
      handleTimerData(it)
    } ?: run {
      setNoDataState()
    }
  }

  private fun handleTimerData(data: Pair<List<Timer>, List<Timer>>) {
    if (data.first.isNotEmpty() || data.second.isNotEmpty()) {
      with(binding) {
        svTimerExist.isVisible = true
        clTimer.isGone = true
      }
      setTimerExistState(data)
    } else {
      setNoDataState()
    }
  }

  private fun setTimerExistState(data: Pair<List<Timer>, List<Timer>>) {
    if (data.first.isEmpty()) {
      setCompleteTimerNotExistState(data.first)
    } else {
      setCompleteTimerExistState(data.first)
    }
    waitTimerAdapter.submitList(data.second)
  }

  private fun setCompleteTimerNotExistState(timers: List<Timer>) {
    with(binding) {
      val color = colorOf(R.color.neutrals100)
      val textColor = colorOf(R.color.neutrals300)
      val colorStateList = ColorStateList.valueOf(color)
      flTimerCompleteCount.backgroundTintList = colorStateList
      tvTimerCompleteCount.setTextColor(textColor)
      tvTimerCompleteCount.text = timers.count().toString()
      tvTimerNotComplete.isVisible = true
      rvTimerComplete.isGone = true
    }
  }

  private fun setCompleteTimerExistState(timers: List<Timer>) {
    with(binding) {
      val color = colorOf(R.color.primary)
      val textColor = colorOf(R.color.white)
      val colorStateList = ColorStateList.valueOf(color)
      flTimerCompleteCount.backgroundTintList = colorStateList
      tvTimerCompleteCount.setTextColor(textColor)
      tvTimerCompleteCount.text = timers.count().toString()
      tvTimerNotComplete.isGone = true
      rvTimerComplete.isVisible = true
      completeTimerAdapter.submitList(timers)
    }
  }

  private fun setNoDataState() {
    with(binding) {
      btnTimerSetEnable.isVisible = true
      btnTimerSetDisable.isGone = true
      clTimer.isVisible = true
      clTimerPermissionOff.isGone = true
      svTimerExist.isGone = true
    }
  }

  private fun collectDeleteState() {
    viewModel.deleteState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          requireContext().linkMindSnackBar(binding.root, "타이머 삭제 완료", false)
        }

        is UiState.Failure -> {}
        is UiState.Empty -> {}
        is UiState.Loading -> {}
      }
    }.launchIn(viewLifeCycleScope)
  }

  private fun requestNotificationPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.POST_NOTIFICATIONS,
      ) == PackageManager.PERMISSION_DENIED
    ) {
      notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
  }

  private fun initCheckNotificationPermission() {
    isNotiPermissionAllowed = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.POST_NOTIFICATIONS,
      ) == PackageManager.PERMISSION_GRANTED
    } else {
      true
    }
  }

  private fun initPlusButtonClickListener() {
    binding.ivTimerPlus.onThrottleClick {
      if (!viewModel.createEnable.value) {
        requireContext().linkMindSnackBar(binding.vTimer2, "알림이 꺼져있어요", true)
        return@onThrottleClick
      }
      findNavController().navigate(org.sopt.timer.R.id.action_navigation_timer_to_navigation_timer_clip_select)
    }
  }

  private fun initSetTimerButtonClickListener() {
    binding.btnTimerSetEnable.onThrottleClick {
      if (!viewModel.createEnable.value) {
        requireContext().linkMindSnackBar(binding.vTimer2, "알림이 꺼져있어요", true)
        return@onThrottleClick
      }
      findNavController().navigate(org.sopt.timer.R.id.action_navigation_timer_to_navigation_timer_clip_select)
    }
  }

  private fun navigateToDestination(destination: String) {
    val (request, navOptions) = DeepLinkUtil.getNavRequestNotPopUpAndOption(
      destination,
      enterAnim = org.sopt.mainfeature.R.anim.from_bottom,
      exitAnim = android.R.anim.fade_out,
      popEnterAnim = android.R.anim.fade_in,
      popExitAnim = org.sopt.mainfeature.R.anim.to_bottom,
    )
    findNavController().navigate(request, navOptions)
  }
}
