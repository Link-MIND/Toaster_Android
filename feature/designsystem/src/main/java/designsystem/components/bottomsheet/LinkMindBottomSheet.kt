package designsystem.components.bottomsheet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.StringRes
import com.google.android.material.bottomsheet.BottomSheetDialog
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import org.sopt.mainfeature.databinding.BottomSheetDialogLinkmindBinding
import org.sopt.ui.view.onThrottleClick

class LinkMindBottomSheet(context: Context) {
  private val binding: BottomSheetDialogLinkmindBinding = BottomSheetDialogLinkmindBinding.inflate(LayoutInflater.from(context))
  private val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context).apply {
    setContentView(binding.root)
  }

  init {
    binding.ivBottomSheetClose.onThrottleClick { dismiss() }

    binding.btnBottomSheet.state = LinkMIndFullWidthButtonState.DISABLE

    bottomSheetDialog.window?.let { window ->
      window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
      window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
      binding.etvBottomSheet.editText.requestFocus()
    }

    binding.etvBottomSheet.apply {
      throttleAfterTextChanged {
        handleTextChange()
      }

      onClickTextClear {
        binding.btnBottomSheet.state = LinkMIndFullWidthButtonState.DISABLE
      }
    }
  }

  fun bottomSheetConfirmBtnClick(onClick: () -> Unit) {
    binding.btnBottomSheet.btnClick {
      onClick()
    }
  }

  private fun handleTextChange() {
    binding.tvBottomSheetErrorText.visibility = if (showErrorMsg()) View.VISIBLE else View.GONE
    binding.btnBottomSheet.state =
      if (!showErrorMsg() && isTextLongEnough()) LinkMIndFullWidthButtonState.ENABLE_PRIMARY else LinkMIndFullWidthButtonState.DISABLE
  }

  private fun isTextLongEnough() = binding.etvBottomSheet.editText.text.length > 1

  fun setTitle(@StringRes textId: Int) {
    binding.tvBottomSheetTitle.setText(textId)
  }

  fun showErrorMsg(): Boolean = binding.etvBottomSheet.editText.text.length > 10

  fun setErroMsg(@StringRes textId: Int) {
    binding.tvBottomSheetErrorText.setText(textId)
  }

  fun show() {
    bottomSheetDialog.show()
  }

  fun dismiss() {
    bottomSheetDialog.dismiss()
  }
}
