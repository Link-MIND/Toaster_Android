package org.sopt.home

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import org.sopt.home.databinding.FragmentHomeBinding
import org.sopt.ui.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.root.setOnClickListener {
      val uri = Uri.parse("featureTimer://fragmentExample")
      findNavController().navigate(uri)
    }
  }
}
