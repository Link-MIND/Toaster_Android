package org.sopt.clip

import android.os.Bundle
import android.view.View
import org.sopt.clip.databinding.FragmentDeleteLinkBottomSheetBinding
import org.sopt.ui.base.BindingBottomSheetDialogFragment

class DeleteLinkBottomSheetFragment :
  BindingBottomSheetDialogFragment<FragmentDeleteLinkBottomSheetBinding>({ FragmentDeleteLinkBottomSheetBinding.inflate(it) }) {
  var id: Int? = null
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.ivDeleteLinkBottomSheetClose.setOnClickListener {
      dismiss()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    id = arguments?.getInt("id")
  }

  companion object {
    fun newInstance(id: Int): DeleteLinkBottomSheetFragment {
      val args = Bundle().apply {
        putInt("id", id)
      }
      return DeleteLinkBottomSheetFragment().apply {
        arguments = args
      }
    }
  }
}
