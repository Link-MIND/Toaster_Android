package org.sopt.clip

import android.os.Bundle
import android.util.Log
import android.view.View
import org.sopt.clip.databinding.FragmentDeleteLinkBottomSheetBinding
import org.sopt.ui.base.BindingBottomSheetDialogFragment
import org.sopt.ui.view.onThrottleClick

class DeleteLinkBottomSheetFragment() :
  BindingBottomSheetDialogFragment<FragmentDeleteLinkBottomSheetBinding>({ FragmentDeleteLinkBottomSheetBinding.inflate(it) }) {
  var id: Int? = null
  private var handleDelete: () -> Unit = {}
  private var handleModify: () -> Unit = {}
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.ivDeleteLinkBottomSheetClose.setOnClickListener {
      dismiss()
    }
    binding.tvDeleteLinkDelete.onThrottleClick {
      Log.d("test", "test")
      handleDelete.invoke()
      dismiss()
    }
    binding.tvDeleteLinkModify.onThrottleClick {
      handleModify.invoke()
      dismiss()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    id = arguments?.getInt("id")
  }

  companion object {
    fun newInstance(id: Int, handleDeleteButton: () -> Unit, handleModifyButton: () -> Unit): DeleteLinkBottomSheetFragment {
      val args = Bundle().apply {
        putInt("id", id)
      }
      return DeleteLinkBottomSheetFragment().apply {
        arguments = args
        handleDelete = handleDeleteButton
        handleModify = handleModifyButton
      }
    }
  }
}
