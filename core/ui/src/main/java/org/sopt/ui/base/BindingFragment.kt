package org.sopt.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<T : ViewBinding> : Fragment() {
  private var _binding: T? = null
  protected val binding
    get() = requireNotNull(_binding) {
    }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = getFragmentBinding(inflater, container)
    return binding.root
  }

  abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): T
  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}
