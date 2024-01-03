package org.sopt.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BindingDialogFragment<T : ViewBinding> : DialogFragment() {
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
    _binding = getFragmentBinding(inflater, container)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): T

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}
