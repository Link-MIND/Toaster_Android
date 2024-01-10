package org.sopt.mypage.my

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import org.sopt.mypage.R
import org.sopt.mypage.databinding.FragmentMyBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.nav.DeepLinkUtil

class MyFragment : BindingFragment<FragmentMyBinding>({ FragmentMyBinding.inflate(it) }) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.ivSetting.setOnClickListener {

      //findNavController().navigate(R.id.action_navigation_my_to_navigation_search)
    }
  }
}
