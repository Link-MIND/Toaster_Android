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
import androidx.lifecycle.lifecycleScope
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

class TimerFragment : BindingFragment<FragmentTimerBinding>({FragmentTimerBinding.inflate(it)}) {
  private val setTimerViewModel: SetTimerViewModel by activityViewModels()
  private val viewModel: TimerViewModel by viewModels()

  private lateinit var completeTimerAdapter: CompleteTimerAdapter
  private lateinit var waitTimerAdapter: WaitTimerAdapter
  private var timerExist = true
  private var isNotiPermissionAllowed = true
  private var isFcmAllowed = false

  private val notificationPermissionLauncher =
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
      isNotiPermissionAllowed = isGranted
      viewModel.setUiState(isGranted)
      if(!isGranted){
        NotificationPermissionDialogFragment().show(parentFragmentManager, this.tag)
      }
    }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    requestNotificationPermission()
    setTimerViewModel.initSetTimer()
    viewModel.setUiState(isNotiPermissionAllowed)
    viewModel.uiState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when(state){
        is TimerUiState.BothAllowed -> {
          Log.e("both","both")
          binding.clTimerPermissionOff.isGone = true
          binding.clTimerNotiPermissionOff.isGone = true
          if(state.data.isNotEmpty()){
            binding.svTimerExist.isVisible = true
            binding.clTimer.isGone = true
          } else {
            binding.btnTimerSetEnable.isVisible = true
            binding.btnTimerSetDisable.isGone = true
            binding.clTimer.isVisible = true
            binding.clTimerPermissionOff.isGone = true
            binding.svTimerExist.isGone = true
          }
        }
        is TimerUiState.AppAllowed -> {
          Log.e("app","app")
          binding.clTimerPermissionOff.isGone = true
          binding.svTimerExist.isGone = true
          binding.clTimer.isGone = true
          binding.clTimerNotiPermissionOff.isVisible = true
        }
        is TimerUiState.NotAllowed -> {
          Log.e("not","not")
          binding.clTimerPermissionOff.isVisible = true
          binding.svTimerExist.isGone = true
          binding.clTimer.isGone = true
          binding.clTimerNotiPermissionOff.isVisible = true
        }
        is TimerUiState.DeviceAllowed -> {
          Log.e("device","device")
          binding.btnTimerSetEnable.isGone = true
          binding.btnTimerSetDisable.isVisible = true
          binding.clTimerPermissionOff.isVisible = true
          binding.svTimerExist.isGone = true
          binding.clTimer.isVisible = true
          binding.clTimerNotiPermissionOff.isGone = true
        }
        is TimerUiState.Loading -> {

        }
        is TimerUiState.Empty -> {

        }
      }
    }.launchIn(viewLifeCycleScope)
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

  override fun onResume() {
    super.onResume()
    initCheckNotificationPermission()
    viewModel.setUiState(isNotiPermissionAllowed)
  }

  private fun requestNotificationPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.POST_NOTIFICATIONS
      ) == PackageManager.PERMISSION_DENIED
    ) {
      notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
  }

  private fun initCheckNotificationPermission() {
    isNotiPermissionAllowed = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.POST_NOTIFICATIONS
      ) == PackageManager.PERMISSION_GRANTED
    } else {
      true
    }
  }
}
