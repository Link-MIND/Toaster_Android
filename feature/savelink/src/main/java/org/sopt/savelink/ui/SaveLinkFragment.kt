package org.sopt.savelink.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import designsystem.components.dialog.LinkMindDialog
import designsystem.components.edittext.state.LinkMindEditTextState
import org.orbitmvi.orbit.viewmodel.observe
import org.sopt.savelink.databinding.FragmentSaveLinkBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.context.hideKeyboard
import org.sopt.ui.keyboard.KeyboardUtils
import org.sopt.ui.keyboard.OnKeyboardVisibilityListener
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class SaveLinkFragment : BindingFragment<FragmentSaveLinkBinding>({ FragmentSaveLinkBinding.inflate(it) }) {
  private val linkMindDialog by lazy {
    LinkMindDialog(requireContext())
  }

  private val viewModel by viewModels<SaveLinkViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
    collectState()
    handleEditTextLink()
    handleKeyboardHide()
    onClickCloseSaveLink()
  }

  private fun initView() {
    val clipboardLink = getArgumentToMain()
    if (clipboardLink.isNotEmpty()) {
      binding.etvSaveCopyLink.editText.setText(clipboardLink)
      handleSaveLinkNextClick()
      binding.btnSaveLinkNext.setBackGround(org.sopt.mainfeature.R.drawable.shape_neutrals850_fill_12_rect)
    } else {
      binding.btnSaveLinkNext.apply {
        state = LinkMIndFullWidthButtonState.DISABLE
        setBackGround(org.sopt.mainfeature.R.drawable.shape_neutrals050_fill_12_rect)
      }
    }
  }

  private fun collectState() {
    viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
  }

  private fun render(homeState: LinkState) {
  }

  private fun handleSideEffect(sideEffect: LinkSideEffect) {
    when (sideEffect) {
      is LinkSideEffect.NavigateUp -> navigateUp()
      is LinkSideEffect.NavigateSetLink -> navigateSetLink()
      is LinkSideEffect.ShowBottomSheet -> showCloseDialog()
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
          btnSaveLinkNext.state = LinkMIndFullWidthButtonState.DISABLE
          if (!editText.text.contains("http")) {
            showErrorState(tvSaveLinkError)
            return@throttleAfterTextChanged
          }
          handleSaveLinkNextClick()
        }
      }
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

  private fun onClickCloseSaveLink() {
    binding.ivSaveLinkClose.onThrottleClick {
      showCloseDialog()
    }
  }

  private fun getArgumentToMain(): String {
    val args: SaveLinkFragmentArgs by navArgs()
    val clipboardLink = args.clipboardLink
    return clipboardLink
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

  private fun showCloseDialog() {
    linkMindDialog.setTitle(org.sopt.mainfeature.R.string.save_clip_dialog_title)
      .setSubtitle(org.sopt.mainfeature.R.string.save_clip_dialog_sub_title)
      .setNegativeButton(org.sopt.mainfeature.R.string.negative_close_msg) {
        linkMindDialog.dismiss()
      }
      .setPositiveButton(org.sopt.mainfeature.R.string.positive_ok_msg) {
        linkMindDialog.dismiss()
        viewModel.navigateUp()
      }
      .show()
  }

  private fun navigateUp() {
    KeyboardUtils.removeKeyboardVisibilityListener(binding.root)
    requireContext().hideKeyboard(binding.root)
    findNavController().navigateUp()
  }

  private fun onClickComplete() {
    binding.btnSaveLinkNext.btnClick {
      viewModel.navigateSetLink()
    }
  }

  private fun navigateSetLink() {
    KeyboardUtils.removeKeyboardVisibilityListener(binding.root)
    val action = SaveLinkFragmentDirections.actionSaveLinkFragmentToSaveLinkSetClipFragment()
    findNavController().navigate(action)
  }
}
