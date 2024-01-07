package org.sopt.savelink.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import designsystem.components.button.state.LinkMindButtonState
import org.sopt.savelink.databinding.FragmentSaveLinkBinding
import org.sopt.savelink.databinding.FragmentSaveLinkSetClipBinding
import org.sopt.ui.base.BindingFragment

class SaveLinkSetClipFragment : BindingFragment<FragmentSaveLinkSetClipBinding>({ FragmentSaveLinkSetClipBinding.inflate(it) }) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.btnSaveLinkComplete.state=LinkMindButtonState.DISABLE
  }

}
