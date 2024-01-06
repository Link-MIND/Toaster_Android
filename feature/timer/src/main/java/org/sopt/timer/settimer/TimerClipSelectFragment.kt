package org.sopt.timer.settimer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import designsystem.components.button.state.LinkMindButtonState
import org.sopt.timer.R
import org.sopt.timer.databinding.FragmentTimerClipSelectBinding
import org.sopt.ui.base.BindingFragment

class TimerClipSelectFragment : BindingFragment<FragmentTimerClipSelectBinding>({ FragmentTimerClipSelectBinding.inflate(it)}){
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnTimerClipSelectNext.state = LinkMindButtonState.DISABLE
  }
}
