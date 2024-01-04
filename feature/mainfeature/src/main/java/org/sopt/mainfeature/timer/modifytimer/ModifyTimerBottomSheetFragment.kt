package org.sopt.mainfeature.timer.modifytimer

import android.os.Bundle
import android.view.View
import org.sopt.mainfeature.databinding.FragmentModifyTimerBottomSheetBinding
import org.sopt.ui.base.BindingBottomSheetDialogFragment

class ModifyTimerBottomSheetFragment :
  BindingBottomSheetDialogFragment<FragmentModifyTimerBottomSheetBinding>({ FragmentModifyTimerBottomSheetBinding.inflate(it) }) {
  var id: Int? = null
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.ivModifyTimerClose.setOnClickListener {
      dismiss()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    id = arguments?.getInt("id")
  }
  companion object {
    fun newInstance(id: Int): ModifyTimerBottomSheetFragment {
      val args = Bundle().apply {
        putInt("id", id)
      }
      return ModifyTimerBottomSheetFragment().apply {
        arguments = args
      }
    }
  }
}
