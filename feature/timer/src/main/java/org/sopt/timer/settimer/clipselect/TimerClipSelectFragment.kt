package org.sopt.timer.settimer.clipselect

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.button.state.LinkMindButtonState
import org.sopt.timer.R
import org.sopt.timer.databinding.FragmentTimerClipSelectBinding
import org.sopt.timer.dummymodel.Clip
import org.sopt.timer.settimer.SetTimerViewModel
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class TimerClipSelectFragment : BindingFragment<FragmentTimerClipSelectBinding>({ FragmentTimerClipSelectBinding.inflate(it) }) {
  private lateinit var adapter: ClipSelectAdapter

  private val viewModel: SetTimerViewModel by activityViewModels()
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val list = viewModel.clipList.value
    initClipSelectAdapter(list)
    initNextButtonClickListener(list)
    initCloseButtonClickListener()
  }
  private fun initClipSelectAdapter(list: List<Clip>) {
    adapter = ClipSelectAdapter(
      onClick = { clip, index ->
        handleClipClick(clip, list, index)
      },
      context = requireContext(),
    )
    adapter.selectedPosition = list.indexOfFirst { it.isSelected }
    adapter.submitList(list)
    binding.btnTimerClipSelectNext.state = if (adapter.selectedPosition != -1) LinkMindButtonState.ENABLE else LinkMindButtonState.DISABLE
    binding.rvItemTimerClipSelect.adapter = adapter
    binding.rvItemTimerClipSelect.itemAnimator = null
  }

  private fun handleClipClick(
    clip: Clip,
    list: List<Clip>,
    index: Int,
  ) {
    if (clip.isSelected) {
      list.onEach { it.isSelected = false }
      list[index].isSelected = true
      binding.btnTimerClipSelectNext.state = LinkMindButtonState.ENABLE
    } else {
      list.onEach { it.isSelected = false }
      binding.btnTimerClipSelectNext.state = LinkMindButtonState.DISABLE
    }
  }

  private fun initNextButtonClickListener(list: List<Clip>) {
    binding.btnTimerClipSelectNext.btnClick {
      viewModel.setClipList(list)
      findNavController().navigate(R.id.action_navigation_timer_clip_select_to_navigation_time_picker)
    }
  }

  private fun initCloseButtonClickListener() {
    binding.ivTimerClipSelectClose.onThrottleClick {
      findNavController().navigateUp()
    }
  }
}
