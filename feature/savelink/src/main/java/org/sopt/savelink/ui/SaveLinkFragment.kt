package org.sopt.savelink.ui

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import org.sopt.savelink.R
import org.sopt.savelink.databinding.FragmentSaveLinkBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class SaveLinkFragment : BindingFragment<FragmentSaveLinkBinding>({ FragmentSaveLinkBinding.inflate(it) }) {
  private lateinit var keyboardVisibilityListener: ViewTreeObserver.OnGlobalLayoutListener
  private lateinit var layoutParams: ViewGroup.MarginLayoutParams

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    KeyboardUtils.setKeyboardVisibilityListener(
      binding.root,
      object : OnKeyboardVisibilityListener {
        override fun onVisibilityChanged(isVisible: Boolean) {
          if (isVisible) {
            layoutParams = binding.btnSaveLinkNext.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.setMargins(0, 0, 0, 0)
            binding.btnSaveLinkNext.layoutParams = layoutParams
          }
          else{
            layoutParams = binding.btnSaveLinkNext.layoutParams as ViewGroup.MarginLayoutParams
            val marginInPixels = (20 * resources.displayMetrics.density).toInt()
            layoutParams.leftMargin = marginInPixels
            layoutParams.rightMargin = marginInPixels
            binding.btnSaveLinkNext.layoutParams = layoutParams
          }
        }
      },
    )
    binding.btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
    handleEditTextLink()
    handleEditTextTitle()
    binding.ivSaveLinkClose.onThrottleClick {
      findNavController().navigateUp()
    }
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

  interface OnKeyboardVisibilityListener {
    fun onVisibilityChanged(visible: Boolean)
  }

  object KeyboardUtils {
    private var keyboardVisibilityListener: ViewTreeObserver.OnGlobalLayoutListener? = null
    fun setKeyboardVisibilityListener(
      parentView: View,
      onKeyboardVisibilityListener: OnKeyboardVisibilityListener
    ) {
      keyboardVisibilityListener = object : ViewTreeObserver.OnGlobalLayoutListener {
        private var alreadyOpen = false
        private val defaultKeyboardHeightDP = 100
        private val estimatedKeyboardDP = defaultKeyboardHeightDP + 48
        private val rect = Rect()

        override fun onGlobalLayout() {
          val estimatedKeyboardHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            estimatedKeyboardDP.toFloat(),
            parentView.resources.displayMetrics
          ).toInt()
          parentView.getWindowVisibleDisplayFrame(rect)
          val heightDiff = parentView.rootView.height - (rect.bottom - rect.top)
          val isShown = heightDiff >= estimatedKeyboardHeight
          if (isShown == alreadyOpen) {
            return
          }
          alreadyOpen = isShown
          onKeyboardVisibilityListener.onVisibilityChanged(isShown)
        }
      }
      parentView.viewTreeObserver.addOnGlobalLayoutListener(keyboardVisibilityListener)
    }

    fun removeKeyboardVisibilityListener(parentView: View) {
      parentView.viewTreeObserver.removeOnGlobalLayoutListener(keyboardVisibilityListener)
      keyboardVisibilityListener = null
    }
  }
}
