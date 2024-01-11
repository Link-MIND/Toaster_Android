package org.sopt.timer.settimer.repeat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import designsystem.components.button.state.LinkMindButtonState
import org.sopt.timer.databinding.FragmentTimerRepeatBinding
import org.sopt.timer.dummymodel.Repeat
import org.sopt.timer.settimer.SetTimerViewModel
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class TimerRepeatFragment : BindingFragment<FragmentTimerRepeatBinding>({ FragmentTimerRepeatBinding.inflate(it) }) {
  lateinit var adapter: TimerRepeatAdapter
  private val viewModel: SetTimerViewModel by activityViewModels()
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val list = viewModel.repeatList.value.map { Repeat(it.period, it.isSelected) }.toMutableList()
    initCompleteButtonState(list)
    initTimerRepeatAdapter(list)
    initCloseButtonClickListener()
    initBackButtonClickListener()
    initCompleteButtonClickListener(list)
  }

  private fun initCompleteButtonState(list: MutableList<Repeat>) {
    if (list.any { it.isSelected }) {
      binding.btnTimerRepeatComplete.state = LinkMindButtonState.ENABLE
    } else {
      binding.btnTimerRepeatComplete.state = LinkMindButtonState.DISABLE
    }
  }

  private fun initTimerRepeatAdapter(list: MutableList<Repeat>) {
    adapter = TimerRepeatAdapter(
      onClick = { repeat, index ->
        handleTimerRepeatItemClick(repeat, list, index)
      },
      context = requireContext(),
    )
    adapter.submitList(list)
    binding.rvTimerRepeat.adapter = adapter
    binding.rvTimerRepeat.itemAnimator = null
  }

  private fun handleTimerRepeatItemClick(
    repeat: Repeat,
    list: MutableList<Repeat>,
    index: Int,
  ) {
    list[index].isSelected = repeat.isSelected
    updateCompleteButtonState(list)
  }

  private fun updateCompleteButtonState(list: MutableList<Repeat>) {
    val newList = list.map {
      it.isSelected
    }
    if (newList.contains(true)) {
      binding.btnTimerRepeatComplete.state = LinkMindButtonState.ENABLE
    } else {
      binding.btnTimerRepeatComplete.state = LinkMindButtonState.DISABLE
    }
  }

  private fun initCloseButtonClickListener() {
    binding.ivTimerRepeatClose.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun initBackButtonClickListener() {
    binding.ivTimerRepeatBack.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun initCompleteButtonClickListener(list: MutableList<Repeat>) {
    binding.btnTimerRepeatComplete.btnClick {
      viewModel.setRepeatList(list)
      findNavController().navigateUp()
    }
  }
}
