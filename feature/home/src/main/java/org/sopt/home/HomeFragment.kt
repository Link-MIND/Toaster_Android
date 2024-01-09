package org.sopt.home

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import designsystem.components.bottomsheet.LinkMindBottomSheet
import org.sopt.home.adapter.HomeClipAdapter
import org.sopt.home.adapter.HomeWeekLinkAdapter
import org.sopt.home.adapter.HomeWeekRecommendLinkAdapter
import org.sopt.home.databinding.FragmentHomeBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {

  private lateinit var homeClipAdapter: HomeClipAdapter
  private lateinit var homeWeekLinkAdapter: HomeWeekLinkAdapter
  private lateinit var homeWeekRecommendLinkAdapter: HomeWeekRecommendLinkAdapter
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.root.setOnClickListener {
//      val (request, navOptions) = DeepLinkUtil.getNavRequestNotPopUpAndOption(
//        "featureTimer://fragmentExample",
//      )
//      val (request, navOptions) = DeepLinkUtil.getNavRequestPopUpAndOption(
//        findNavController().graph.id,
//        false,
//        "featureTimer://fragmentExample",
//      )
//      findNavController().navigate(request, navOptions)
      binding.clHomeSearch.onThrottleClick {
        //Todo
      }
    }
    initAdapter()
    val list = listOf(ClipDummy("전체클립", 1), ClipDummy("Title", 2), ClipDummy("LeeSak", 3), null)
    val list2 = listOf(
      WeekLinkDummy("Title", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
      WeekLinkDummy("Category", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
      WeekLinkDummy("LeeSak", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
    )
    homeClipAdapter.submitList(list)
    homeWeekLinkAdapter.submitList(list2)
    homeWeekRecommendLinkAdapter.submitList(list2)
    binding.pbLinkmindHome.setProgressBarMain(54)
    navigateToSetting()
  }

  private fun navigateToSetting() {
    binding.ivHomeSetting.onThrottleClick {
      navigateToDestination("featureMyPage://fragmentSetting")
    }
  }

  private fun navigateToDestination(destination: String) {
    val uri = Uri.parse(destination)
    findNavController().navigate(uri)
  }

  private fun initAdapter() {
    setClipAdapter()
    setWeekLinkAdapter()
    setWeekRecommendAdapter()
  }

  private fun setClipAdapter() {
    homeClipAdapter = HomeClipAdapter(
      onClickItemClip = {},
      onClickItemClip2 = {
        showHomeBottomSheet()
      },
    )
    binding.rvHomeClip.adapter = homeClipAdapter
    val spacingClipInPixels = resources.getDimensionPixelSize(R.dimen.spacing_11)
    binding.rvHomeClip.addItemDecoration(ItemDecoration(2, spacingClipInPixels))
  }

  private fun setWeekLinkAdapter() {
    homeWeekLinkAdapter = HomeWeekLinkAdapter(onClickItem = {})
    binding.rvWeekLink.adapter = homeWeekLinkAdapter
  }

  private fun setWeekRecommendAdapter() {
    homeWeekRecommendLinkAdapter = HomeWeekRecommendLinkAdapter(onClickItem = {})
    binding.rvHomeWeekRecommend.adapter = homeWeekRecommendLinkAdapter
    val spacingWeekRecommendInPixels = resources.getDimensionPixelSize(R.dimen.spacing_12)
    binding.rvHomeWeekRecommend.addItemDecoration(ItemDecoration(3, spacingWeekRecommendInPixels))
  }

  private fun showHomeBottomSheet() {
    val linkMindBottomSheet = LinkMindBottomSheet(requireContext())
    linkMindBottomSheet.show()
    linkMindBottomSheet.apply {
      setBottomSheetHint(org.sopt.mainfeature.R.string.home_new_clip_info)
      setTitle(org.sopt.mainfeature.R.string.home_correction_clip)
      setErroMsg(org.sopt.mainfeature.R.string.home_error_clip_info)
      bottomSheetConfirmBtnClick {
        Log.d("test", "test")
      }
    }
  }
}
