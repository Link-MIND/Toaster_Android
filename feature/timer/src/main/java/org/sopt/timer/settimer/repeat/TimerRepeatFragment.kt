package org.sopt.timer.settimer.repeat

import android.os.Bundle
import android.util.Log
import android.view.View
import designsystem.components.button.state.LinkMindButtonState
import org.sopt.timer.databinding.FragmentTimerRepeatBinding
import org.sopt.timer.dummymodel.Repeat
import org.sopt.ui.base.BindingFragment

class TimerRepeatFragment : BindingFragment<FragmentTimerRepeatBinding>({ FragmentTimerRepeatBinding.inflate(it) }) {
  lateinit var adapter: TimerRepeatAdapter
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.btnTimerRepeatComplete.state = LinkMindButtonState.DISABLE

    val list = listOf(
      Repeat("매일 (월~일)", false),
      Repeat("주중마다 (월~금)", false),
      Repeat("주말마다 (토~일)", false),
      Repeat("월요일마다", false),
      Repeat("화요일마다", false),
      Repeat("수요일마다", false),
      Repeat("목요일마다", false),
      Repeat("금요일마다", false),
      Repeat("토요일마다", false),
      Repeat("일요일마다", false),
    )

    adapter = TimerRepeatAdapter(
      onClick = { a, b ->
        if (a.isSelected) {
          list[b].isSelected = true
          Log.e("리스트", "$list")
        } else {
          list[b].isSelected = false
          Log.e("리스트", "$list")
        }
        val newList = list.map {
          it.isSelected
        }
        if (newList.contains(true)) {
          binding.btnTimerRepeatComplete.state = LinkMindButtonState.ENABLE
        } else {
          binding.btnTimerRepeatComplete.state = LinkMindButtonState.DISABLE
        }
      },
      context = requireContext(),
    )
    adapter.submitList(list)
    binding.rvTimerRepeat.adapter = adapter
  }
}
