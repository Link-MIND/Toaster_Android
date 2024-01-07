package org.sopt.timer.settimer

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import designsystem.components.button.state.LinkMindButtonState
import org.sopt.timer.R
import org.sopt.timer.databinding.FragmentTimerClipSelectBinding
import org.sopt.timer.dummymodel.Clip
import org.sopt.ui.base.BindingFragment

class TimerClipSelectFragment : BindingFragment<FragmentTimerClipSelectBinding>({ FragmentTimerClipSelectBinding.inflate(it) }) {
  lateinit var adapter: ClipSelectAdapter
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnTimerClipSelectNext.state = LinkMindButtonState.DISABLE

    var list = listOf(
      Clip("전체 클립", 3, false),
      Clip("전체 클립", 3, false),
      Clip("전체 클립", 3, false),
      Clip("전체 클립", 3, false),
    )
    adapter = ClipSelectAdapter(
      onClick = { a, b ->
        if (a.isSelected) {
          list.onEach { it.isSelected = false }
          list[b].isSelected = true
          Log.e("리스트", "$list")
          binding.btnTimerClipSelectNext.state = LinkMindButtonState.ENABLE
        } else {
          list.onEach { it.isSelected = false }
          Log.e("리스트", "$list")
          binding.btnTimerClipSelectNext.state = LinkMindButtonState.DISABLE
        }
      },
      context = requireContext(),
    )
    adapter.submitList(list)
    binding.rvItemTimerClipSelect.adapter = adapter
    binding.btnTimerClipSelectNext.btnClick {
      findNavController().navigate(R.id.action_navigation_timer_clip_select_to_navigation_time_picker)
    }
  }
}
