package org.sopt.timer.modifytimer

import android.os.Bundle
import android.view.View
import designsystem.components.dialog.LinkMindDialog
import org.sopt.timer.R
import org.sopt.timer.databinding.FragmentModifyTimerBottomSheetBinding
import org.sopt.ui.base.BindingBottomSheetDialogFragment
import org.sopt.ui.view.onThrottleClick

class ModifyTimerBottomSheetFragment :
  BindingBottomSheetDialogFragment<FragmentModifyTimerBottomSheetBinding>({ FragmentModifyTimerBottomSheetBinding.inflate(it) }) {
  var id: Int? = null
  private var handleDelete: () -> Unit = {}
  private var handleModify: () -> Unit = {}
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.ivModifyTimerClose.setOnClickListener {
      dismiss()
    }

    binding.tvModifyTimerModify.onThrottleClick {
      handleModify.invoke()
      dismiss()
    }

    binding.tvModifyTimerDelete.onThrottleClick {
      val linkMindDialog = LinkMindDialog(requireContext())
      linkMindDialog.setTitle(org.sopt.mainfeature.R.string.timer_delete_dialog_title)
        .setSubtitle(org.sopt.mainfeature.R.string.timer_delete_dialog_sub_title)
        .setNegativeButton(org.sopt.mainfeature.R.string.negative_close_cancel) {
          linkMindDialog.dismiss()
        }
        .setPositiveButton(org.sopt.mainfeature.R.string.positive_delete) {
          linkMindDialog.dismiss()
          handleDelete.invoke()
          dismiss()
        }
        .show()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    id = arguments?.getInt("id")
  }

  companion object {
    fun newInstance(
      id: Int,
      handleDeleteButton: () -> Unit,
      handleModifyButton: () -> Unit,
    ): ModifyTimerBottomSheetFragment {
      val args = Bundle().apply {
        putInt("id", id)
      }
      return ModifyTimerBottomSheetFragment().apply {
        arguments = args
        handleDelete = handleDeleteButton
        handleModify = handleModifyButton
      }
    }
  }
}
