package org.sopt.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<T : ViewBinding>(
  private val inflater: (LayoutInflater) -> T,
) : Fragment() {
  private var _binding: T? = null
  protected val binding
    get() = requireNotNull(_binding) {
      Log.d("error", "$_binding")
    }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = inflater(layoutInflater)
    return binding.root
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}
