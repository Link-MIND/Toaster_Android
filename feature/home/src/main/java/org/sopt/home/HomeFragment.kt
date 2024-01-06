package org.sopt.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import org.sopt.home.databinding.FragmentHomeBinding
import org.sopt.ui.DeepLinkUtil
import org.sopt.ui.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {

  private lateinit var homeClipAdapter: HomeClipAdapter
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.root.setOnClickListener {
      val (request, navOptions) = DeepLinkUtil.getNavRequestNotPopUpAndOption(
        "featureTimer://fragmentExample",
      )
//      val (request, navOptions) = DeepLinkUtil.getNavRequestPopUpAndOption(
//        findNavController().graph.id,
//        false,
//        "featureTimer://fragmentExample",
//      )
      findNavController().navigate(request, navOptions)
    }
    homeClipAdapter = HomeClipAdapter(onClickItemClip = {})
    val list = listOf<ClipDummy>(ClipDummy("test", 3), ClipDummy("test", 3), ClipDummy("test", 3))
    homeClipAdapter.submitList(list)
    binding.rvHomeClip.adapter = homeClipAdapter

  }
}
