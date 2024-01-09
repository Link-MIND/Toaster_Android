package org.sopt.savelink.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import org.sopt.savelink.R
import org.sopt.savelink.databinding.FragmentSaveLinkBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.keyboard.KeyboardUtils
import org.sopt.ui.keyboard.OnKeyboardVisibilityListener
import org.sopt.ui.view.onThrottleClick

class SaveLinkFragment : BindingFragment<FragmentSaveLinkBinding>({ FragmentSaveLinkBinding.inflate(it) }) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    handleKeyboardHide()
    binding.btnSaveLinkNext.apply {
      state = LinkMIndFullWidthButtonState.DISABLE
      test(org.sopt.mainfeature.R.drawable.shape_neutrals050_fill_12_rect)
    }
    handleEditTextLink()
    handleEditTextTitle()
    binding.ivSaveLinkClose.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun handleKeyboardHide() {

    val layoutParams = binding.btnSaveLinkNext.layoutParams as ViewGroup.MarginLayoutParams
    KeyboardUtils.setKeyboardVisibilityListener(
      binding.root,
      object : OnKeyboardVisibilityListener {
        override fun onVisibilityChanged(isVisible: Boolean) {
          if (isVisible) {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.setMargins(0, 0, 0, 0)
            binding.btnSaveLinkNext.layoutParams = layoutParams
            binding.btnSaveLinkNext.apply {
              state = if (state == LinkMIndFullWidthButtonState.DISABLE)
                LinkMIndFullWidthButtonState.DISABLE
              else
                LinkMIndFullWidthButtonState.ENABLE_BLACK
            }
          } else {
            val marginInPixels = (20 * resources.displayMetrics.density).toInt()
            layoutParams.leftMargin = marginInPixels
            layoutParams.rightMargin = marginInPixels
            binding.btnSaveLinkNext.layoutParams = layoutParams
            binding.btnSaveLinkNext.apply {
              if (state == LinkMIndFullWidthButtonState.DISABLE)
                test(org.sopt.mainfeature.R.drawable.shape_neutrals050_fill_12_rect)
              else
                test(org.sopt.mainfeature.R.drawable.shape_neutrals850_fill_12_rect)
            }
          }
        }
      },
    )
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
            KeyboardUtils.removeKeyboardVisibilityListener(binding.root)
            findNavController().navigate(R.id.action_saveLinkFragment_to_saveLinkSetClipFragment)
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
