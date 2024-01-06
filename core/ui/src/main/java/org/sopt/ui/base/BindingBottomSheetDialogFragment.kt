package org.sopt.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BindingBottomSheetDialogFragment<T : ViewBinding>(
  private val inflater: (LayoutInflater) -> T,
) : BottomSheetDialogFragment() {
  private var _binding: T? = null
  protected val binding get() = requireNotNull(_binding) { { "binding object is not initialized" } }

  override fun onStart() {
    super.onStart()
    dialog?.window?.apply {
      setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT,
      )
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    _binding = inflater(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}
