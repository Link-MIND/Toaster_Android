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
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.mainfeature.R
import org.sopt.timer.databinding.FragmentTimerBinding
import org.sopt.timer.dummymodel.Timer
import org.sopt.timer.modifytimer.ModifyTimerBottomSheetFragment
import org.sopt.timer.settimer.SetTimerViewModel
import org.sopt.timer.settimer.TimerUiState
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.fragment.colorOf
import org.sopt.ui.fragment.snackBar
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope

class TimerFragment : BindingFragment<FragmentTimerBinding>({ FragmentTimerBinding.inflate(it) }) {
  private val setTimerViewModel: SetTimerViewModel by activityViewModels()
  private val viewModel: TimerViewModel by viewModels()

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
    collectUiState()
    completeTimerAdapter = CompleteTimerAdapter({ snackBar(binding.root, { "안녕" }) })
    waitTimerAdapter = WaitTimerAdapter({}, { ModifyTimerBottomSheetFragment.newInstance(it.id).show(parentFragmentManager, this.tag) })

    val list = listOf(
      Timer(1, "네이버", "일요일", true, 8, 37),
      Timer(1, "네이버", "일요일", true, 8, 37),
    )
    // val list = emptyList<Timer>()
    completeTimerAdapter.submitList(list)
    waitTimerAdapter.submitList(list)
    binding.tvTimerCompleteCount.text = list.count().toString()
    if (list.count() != 0) {
      val color = colorOf(R.color.primary)
      val textColor = colorOf(R.color.white)
      val colorStateList = ColorStateList.valueOf(color)
      binding.flTimerCompleteCount.backgroundTintList = colorStateList
      binding.tvTimerCompleteCount.setTextColor(textColor)
      binding.tvTimerNotComplete.isGone = true
    } else {
      binding.tvTimerNotComplete.isVisible = true
    }
    binding.rvTimerComplete.adapter = completeTimerAdapter
    binding.rvTimerWait.adapter = waitTimerAdapter

    binding.ivTimerPlus.setOnClickListener {
      findNavController().navigate(org.sopt.timer.R.id.action_navigation_timer_to_navigation_timer_clip_select)
    }
  }

  private fun collectUiState() {
    viewModel.uiState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is TimerUiState.BothAllowed -> {
          handleBothAllowedState(state)
        }

        is TimerUiState.AppAllowed -> {
          handleAppAllowedState()
        }

        is TimerUiState.NotAllowed -> {
          handleNotAllowedState()
        }

        is TimerUiState.DeviceAllowed -> {
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
    Log.e("device", "device")
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
    Log.e("not", "not")
    with(binding) {
      clTimerPermissionOff.isVisible = true
      svTimerExist.isGone = true
      clTimer.isGone = true
      clTimerNotiPermissionOff.isVisible = true
    }
  }

  private fun handleAppAllowedState() {
    Log.e("app", "app")
    with(binding) {
      clTimerPermissionOff.isGone = true
      svTimerExist.isGone = true
      clTimer.isGone = true
      clTimerNotiPermissionOff.isVisible = true
    }
  }

  private fun handleBothAllowedState(state: TimerUiState.BothAllowed<List<Timer>>) {
    Log.e("both", "both")
    with(binding) {
      clTimerPermissionOff.isGone = true
      clTimerNotiPermissionOff.isGone = true
      if (state.data.isNotEmpty()) {
        svTimerExist.isVisible = true
        clTimer.isGone = true
        return
      }
      btnTimerSetEnable.isVisible = true
      btnTimerSetDisable.isGone = true
      clTimer.isVisible = true
      clTimerPermissionOff.isGone = true
      svTimerExist.isGone = true
    }
  }

  override fun onResume() {
    super.onResume()
    initCheckNotificationPermission()
    viewModel.setUiState(isNotiPermissionAllowed)
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
}
