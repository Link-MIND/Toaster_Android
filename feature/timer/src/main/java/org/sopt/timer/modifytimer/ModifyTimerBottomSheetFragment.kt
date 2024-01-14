package org.sopt.timer.modifytimer

import android.os.Bundle
import android.view.View
import org.sopt.timer.databinding.FragmentModifyTimerBottomSheetBinding
import org.sopt.ui.base.BindingBottomSheetDialogFragment
import org.sopt.ui.view.onThrottleClick

class ModifyTimerBottomSheetFragment :
  BindingBottomSheetDialogFragment<FragmentModifyTimerBottomSheetBinding>({ FragmentModifyTimerBottomSheetBinding.inflate(it) }) {
  var id: Int? = null
  private var handleDelete: () -> Unit = {}
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.ivModifyTimerClose.setOnClickListener {
      dismiss()
    }

    binding.tvModifyTimerDelete.onThrottleClick {
      handleDelete.invoke()
      dismiss()
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
    ): ModifyTimerBottomSheetFragment {
      val args = Bundle().apply {
        putInt("id", id)
      }
      return ModifyTimerBottomSheetFragment().apply {
        arguments = args
        handleDelete = handleDeleteButton
      }
    }
  }
}
