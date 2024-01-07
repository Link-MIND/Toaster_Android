package org.sopt.savelink

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import org.sopt.savelink.databinding.FragmentSaveLinkBinding
import org.sopt.ui.base.BindingFragment

class SaveLinkFragment : BindingFragment<FragmentSaveLinkBinding>({ FragmentSaveLinkBinding.inflate(it) }) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
    handleEditTextLink()
    handleEditTextTitle()
  }
  private fun handleEditTextLink() {
    with(binding) {
      etvSaveCopyLink.apply {
        onClickTextClear {
          hideErrorStateDisable(tvSaveLinkError)
          hideSubTitleAndLinkTitle()
        }
        throttleAfterTextChanged {
          hideSubTitleAndLinkTitle()
          if (checkTextLength(15)) {
            showErrorState(tvSaveLinkError)
            return@throttleAfterTextChanged
          }
          handleSaveLinkNextClick()
        }
      }
    }
  }

  private fun handleEditTextTitle() {
    binding.etvSaveCopyLinkTitle.apply {
      onClickTextClear {
        hideErrorStateDisable(binding.tvSaveLinkErrorTitle)
      }
      throttleAfterTextChanged {
        if (checkTextLength(15)) {
          showErrorState(binding.tvSaveLinkErrorTitle)
          return@throttleAfterTextChanged
        }
        hideErrorState(binding.tvSaveLinkErrorTitle)
        binding.btnSaveLinkNext.btnClick {
          with(binding) {
            Log.d("test", "success")
          }
        }
      }
    }
  }

  private fun hideErrorStateDisable(errorText: TextView) {
    errorText.isGone = true
    binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
  }

  private fun showErrorState(errorText: TextView) {
    errorText.isVisible = true
    binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
  }

  private fun hideErrorState(errorText: TextView) {
    errorText.isGone = true
    binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.ENABLE_BLACK
  }

  private fun hideSubTitleAndLinkTitle() {
    binding.tvSaveLinkSubTitle.isGone = true
    binding.etvSaveCopyLinkTitle.isGone = true
  }

  private fun handleSaveLinkNextClick() {
    with(binding) {
      hideErrorState(tvSaveLinkError)
      btnSaveLinkNext.btnClick {
        if (etvSaveCopyLink.editText.text.isNotEmpty()) {
          disableSaveLinkNextAndShowSubTitleAndLinkTitle()
          return@btnClick
        }
        showErrorState(binding.tvSaveLinkError)
      }
    }
  }

  private fun disableSaveLinkNextAndShowSubTitleAndLinkTitle() {
    binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
    binding.tvSaveLinkSubTitle.isVisible = true
    binding.etvSaveCopyLinkTitle.isVisible = true
  }

}
