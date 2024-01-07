package org.sopt.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import org.sopt.home.adapter.HomeClipAdapter
import org.sopt.home.adapter.HomeWeekLinkAdapter
import org.sopt.home.adapter.HomeWeekRecommendLinkAdapter
import org.sopt.home.databinding.FragmentHomeBinding
import org.sopt.ui.DeepLinkUtil
import org.sopt.ui.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {

  private lateinit var homeClipAdapter: HomeClipAdapter
  private lateinit var homeWeekLinkAdapter: HomeWeekLinkAdapter
  private lateinit var homeWeekRecommendLinkAdapter: HomeWeekRecommendLinkAdapter
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
    initAdapter()
    val list = listOf(ClipDummy("1", 1), ClipDummy("2", 2), ClipDummy("3", 3), null)
    val list2 = listOf(
      WeekLinkDummy("1", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
      WeekLinkDummy("1", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
      WeekLinkDummy("1", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
    )
    homeClipAdapter.submitList(list)
    homeWeekLinkAdapter.submitList(list2)
    homeWeekRecommendLinkAdapter.submitList(list2)
  }
  private fun initAdapter() {
    setClipAdapter()
    setWeekLinkAdapter()
    setWeekRecommendAdapter()
  }

  private fun setClipAdapter() {
    homeClipAdapter = HomeClipAdapter(onClickItemClip = {}, onClickItemClip2 = {})
    binding.rvHomeClip.adapter = homeClipAdapter
    val spacingClipInPixels = resources.getDimensionPixelSize(R.dimen.spacing_11)
    binding.rvHomeClip.addItemDecoration(GridSpacingItemDecoration(2, spacingClipInPixels, false))
  }

  private fun setWeekLinkAdapter() {
    homeWeekLinkAdapter = HomeWeekLinkAdapter(onClickItem = {})
    binding.rvWeekLink.adapter = homeWeekLinkAdapter
  }

  private fun setWeekRecommendAdapter() {
    homeWeekRecommendLinkAdapter = HomeWeekRecommendLinkAdapter(onClickItem = {})
    binding.rvHomeWeekRecommend.adapter = homeWeekRecommendLinkAdapter
    val spacingWeekRecommendInPixels = resources.getDimensionPixelSize(R.dimen.spacing_12)
    binding.rvHomeWeekRecommend.addItemDecoration(GridSpacingItemDecoration(3, spacingWeekRecommendInPixels, false))
  }
}
