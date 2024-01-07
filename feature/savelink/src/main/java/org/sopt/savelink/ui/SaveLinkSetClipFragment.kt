package org.sopt.savelink.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import designsystem.components.bottomsheet.LinkMindBottomSheet
import designsystem.components.button.state.LinkMindButtonState
import org.sopt.mainfeature.R
import org.sopt.savelink.databinding.FragmentSaveLinkSetClipBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class SaveLinkSetClipFragment : BindingFragment<FragmentSaveLinkSetClipBinding>({ FragmentSaveLinkSetClipBinding.inflate(it) }) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.btnSaveLinkComplete.state = LinkMindButtonState.DISABLE
    binding.tvSaveLinkAddClip.onThrottleClick {
      val linkMindBottomSheet = LinkMindBottomSheet(requireContext())
      linkMindBottomSheet.show()
      linkMindBottomSheet.apply {
        setTitle(R.string.text_clip)
        setErroMsg(R.string.text_clip)
        bottomSheetConfirmBtnClick {
          Log.d("test", "test")
        }
      }
    }
  }
}
