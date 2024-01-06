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
    initClipAdapter()
    val list = listOf(ClipDummy("1", 1), ClipDummy("2", 2), ClipDummy("3", 3), null)
    homeClipAdapter.submitList(list)
  }
  private fun initClipAdapter() {
    homeClipAdapter = HomeClipAdapter(onClickItemClip = {}, onClickItemClip2 = {})
    binding.rvHomeClip.adapter = homeClipAdapter
  }
}
