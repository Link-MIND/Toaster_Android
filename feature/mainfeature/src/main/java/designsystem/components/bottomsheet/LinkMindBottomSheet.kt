package designsystem.components.bottomsheet

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.BottomSheetDialogLinkmindBinding

class LinkMindBottomSheet(context: Context) {
  private val binding: BottomSheetDialogLinkmindBinding = BottomSheetDialogLinkmindBinding.inflate(LayoutInflater.from(context))
  private val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)

  init {
    bottomSheetDialog.setContentView(binding.root)
  }

  fun setTitle(@StringRes textId:Int){
    binding.tvBottomSheetTitle.setText(textId)
  }
  fun show() {
    bottomSheetDialog.show()
  }

  fun dismiss() {
    bottomSheetDialog.dismiss()
  }
}

