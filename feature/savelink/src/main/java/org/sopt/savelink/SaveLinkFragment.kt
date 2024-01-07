package org.sopt.savelink

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import org.sopt.savelink.databinding.FragmentSaveLinkBinding
import org.sopt.ui.base.BindingFragment

class SaveLinkFragment : BindingFragment<FragmentSaveLinkBinding>({ FragmentSaveLinkBinding.inflate(it) }) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.etvSaveCopyLink.apply {
      onClickTextClear { }
      throttleAfterTextChanged {
        if (checkTextLength(15)) {
          binding.tvSaveLinkError.isVisible = true
        } else {
          binding.tvSaveLinkError.isGone = true
          binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.ENABLE_BLACK
          binding.btnSaveLinkNext.btnClick {
            with(binding) {
              btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
              tvSaveLinkSubTitle.isVisible = true
              etvSaveCopyLinkTitle.isVisible = true
            }
          }
        }
      }
    }
    binding.etvSaveCopyLinkTitle.apply {
      onClickTextClear { }
      if (checkTextLength(15)) {
        binding.tvSaveLinkError.isVisible = true
      }
    }
    binding.btnSaveLinkNext.apply {
      state = LinkMIndFullWidthButtonState.DISABLE
    }
//    binding.root.setOnClickListener {
//      findNavController().navigateUp()
//    }
  }
}
