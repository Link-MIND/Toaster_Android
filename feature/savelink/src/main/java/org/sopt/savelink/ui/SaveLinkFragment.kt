package org.sopt.savelink.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import designsystem.components.edittext.state.LinkMindEditTextState
import org.sopt.savelink.R
import org.sopt.savelink.databinding.FragmentSaveLinkBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.keyboard.KeyboardUtils
import org.sopt.ui.keyboard.OnKeyboardVisibilityListener
import org.sopt.ui.view.onThrottleClick

class SaveLinkFragment : BindingFragment<FragmentSaveLinkBinding>({ FragmentSaveLinkBinding.inflate(it) }) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
    handleEditTextLink()
    handleKeyboardHide()
    navigateUp()
  }

  private fun initView() {
    binding.btnSaveLinkNext.apply {
      state = LinkMIndFullWidthButtonState.DISABLE
      setBackGround(org.sopt.mainfeature.R.drawable.shape_neutrals050_fill_12_rect)
    }
  }

  private fun handleEditTextLink() {
    with(binding) {
      etvSaveCopyLink.apply {
        onClickTextClear {
          hideErrorState(tvSaveLinkError, LinkMIndFullWidthButtonState.DISABLE)
          binding.btnSaveLinkNext.setBackGround(org.sopt.mainfeature.R.drawable.shape_neutrals050_fill_12_rect)
        }
        throttleAfterTextChanged {
          if (checkTextLength(15)) {
            showErrorState(tvSaveLinkError)
            return@throttleAfterTextChanged
          }
          handleSaveLinkNextClick()
        }
      }
    }
  }

  private fun onClickComplete() {
    binding.btnSaveLinkNext.btnClick {
      KeyboardUtils.removeKeyboardVisibilityListener(binding.root)
      findNavController().navigate(R.id.action_saveLinkFragment_to_saveLinkSetClipFragment)
    }
  }

  private fun hideErrorState(errorText: TextView, state: LinkMIndFullWidthButtonState) {
    errorText.isGone = true
    binding.etvSaveCopyLink.state = LinkMindEditTextState.ENABLE
    binding.btnSaveLinkNext.state = state
  }

  private fun showErrorState(errorText: TextView) {
    errorText.isVisible = true
    binding.etvSaveCopyLink.state = LinkMindEditTextState.ERROR
    binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
  }

  private fun handleSaveLinkNextClick() {
    with(binding) {
      hideErrorState(tvSaveLinkError, LinkMIndFullWidthButtonState.ENABLE_BLACK)
      onClickComplete()
    }
  }

  private fun handleKeyboardHide() {
    val layoutParams = binding.btnSaveLinkNext.layoutParams as ViewGroup.MarginLayoutParams
    KeyboardUtils.setKeyboardVisibilityListener(
      binding.root,
      object : OnKeyboardVisibilityListener {
        override fun onVisibilityChanged(isVisible: Boolean) {
          if (isVisible) {
            handleKeyboardVisible(layoutParams)
          } else {
            handleKeyboardHidden(layoutParams)
          }
        }
      },
    )
  }

  private fun handleKeyboardVisible(layoutParams: ViewGroup.MarginLayoutParams) {
    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
    layoutParams.setMargins(0, 0, 0, 0)
    binding.btnSaveLinkNext.layoutParams = layoutParams
    binding.btnSaveLinkNext.apply {
      state = if (state == LinkMIndFullWidthButtonState.DISABLE) {
        LinkMIndFullWidthButtonState.DISABLE
      } else {
        LinkMIndFullWidthButtonState.ENABLE_BLACK
      }
    }
  }

  private fun handleKeyboardHidden(layoutParams: ViewGroup.MarginLayoutParams) {
    val marginInPixels = (20 * resources.displayMetrics.density).toInt()
    layoutParams.leftMargin = marginInPixels
    layoutParams.rightMargin = marginInPixels
    binding.btnSaveLinkNext.layoutParams = layoutParams
    binding.btnSaveLinkNext.apply {
      if (state == LinkMIndFullWidthButtonState.DISABLE) {
        setBackGround(org.sopt.mainfeature.R.drawable.shape_neutrals050_fill_12_rect)
      } else {
        setBackGround(org.sopt.mainfeature.R.drawable.shape_neutrals850_fill_12_rect)
      }
    }
  }

  private fun navigateUp() {
    binding.ivSaveLinkClose.onThrottleClick {
      findNavController().navigateUp()
    }
  }
}
