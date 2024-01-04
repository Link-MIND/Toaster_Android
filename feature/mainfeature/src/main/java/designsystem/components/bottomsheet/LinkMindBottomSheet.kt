package designsystem.components.bottomsheet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.sopt.mainfeature.databinding.BottomSheetDialogLinkmindBinding
import org.sopt.ui.view.onThrottleClick

class LinkMindBottomSheet(context: Context) {
  private val binding: BottomSheetDialogLinkmindBinding = BottomSheetDialogLinkmindBinding.inflate(LayoutInflater.from(context))
  private val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)

  init {
    bottomSheetDialog.setContentView(binding.root)

    binding.ivBottomSheetClose.onThrottleClick {
      dismiss()
    }
    binding.etvBottomSheet.apply {
      throttleAfterTextChanged {
        if (showErrorMsg()) binding.tvBottomSheetErrorText.visibility = View.VISIBLE
        else View.GONE
      }
    }
  }

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

