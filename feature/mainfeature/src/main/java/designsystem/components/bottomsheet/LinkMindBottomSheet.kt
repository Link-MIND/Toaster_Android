package designsystem.components.bottomsheet

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.sopt.mainfeature.databinding.BottomSheetDialogLinkmindBinding

class LinkMindBottomSheet(context: Context) {
  private val binding: BottomSheetDialogLinkmindBinding = BottomSheetDialogLinkmindBinding.inflate(LayoutInflater.from(context))
  private val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)

  init {
    bottomSheetDialog.setContentView(binding.root)
  }

  fun show() {
    bottomSheetDialog.show()
  }

  fun dismiss() {
    bottomSheetDialog.dismiss()
  }
}

