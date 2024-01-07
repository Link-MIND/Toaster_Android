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
      onClickTextClear {
        binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
      }
      throttleAfterTextChanged {
        if (checkTextLength(15)) {
          binding.tvSaveLinkError.isVisible = true
          binding.tvSaveLinkSubTitle.isGone = true
          binding.etvSaveCopyLinkTitle.isGone = true
          binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
        } else {
          binding.tvSaveLinkError.isGone = true
          binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.ENABLE_BLACK
          binding.btnSaveLinkNext.btnClick {
            with(binding) {
              if (binding.etvSaveCopyLink.editText.text.isNotEmpty()) {
                btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
                tvSaveLinkSubTitle.isVisible = true
                etvSaveCopyLinkTitle.isVisible = true
              } else {
                btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
                tvSaveLinkError.isVisible = true
              }
            }
          }
        }
      }
    }
    binding.etvSaveCopyLinkTitle.apply {
      onClickTextClear { }
      throttleAfterTextChanged {
        if (checkTextLength(15)) {
          binding.tvSaveLinkErrorTitle.isVisible = true
        } else {
          binding.tvSaveLinkErrorTitle.isGone = true
          binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.ENABLE_BLACK
          binding.btnSaveLinkNext.btnClick {
            with(binding) {

            }
          }
        }
      }
    }
    binding.btnSaveLinkNext.apply {
      state = LinkMIndFullWidthButtonState.DISABLE
    }
  }
}
