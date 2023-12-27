package org.sopt.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BindingDialogFragment<T : ViewDataBinding>(
  @LayoutRes val layoutRes: Int,
) : DialogFragment() {
  private var _binding: T? = null
  protected val binding get() = requireNotNull(_binding) { { "binding object is not initialized" } }

  override fun onStart() {
    super.onStart()
    dialog?.window?.apply {
      setLayout(
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
      )
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.lifecycleOwner = viewLifecycleOwner
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}
