package org.sopt.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.bottomsheet.LinkMindBottomSheet
import designsystem.components.toast.linkMindSnackBar
import org.sopt.home.adapter.HomeClipAdapter
import org.sopt.home.adapter.HomeWeekLinkAdapter
import org.sopt.home.adapter.HomeWeekRecommendLinkAdapter
import org.sopt.home.adapter.ItemDecoration
import org.sopt.home.databinding.FragmentHomeBinding
import org.sopt.model.category.Category
import org.sopt.model.home.RecommendLink
import org.sopt.model.home.WeekBestLink
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.nav.DeepLinkUtil
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {

  private lateinit var homeClipAdapter: HomeClipAdapter
  private lateinit var homeWeekLinkAdapter: HomeWeekLinkAdapter
  private lateinit var homeWeekRecommendLinkAdapter: HomeWeekRecommendLinkAdapter
  private val viewModel by viewModels<HomeViewModel>()
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.getRecommendSite()
    viewModel.getWeekBestLink()
    viewModel.getMainPageUserClip()
    initAdapter()
    val list = listOf(
      Category(1, "전체클립", 1),
      Category(2, "TitleCheck", 1),
      Category(3, "LeeSak", 3),
      null,
    )
    val list2 = listOf(
      WeekBestLink(1, "Title", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
      WeekBestLink(2, "Category", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
      WeekBestLink(3, "LeeSak", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
    )
    val list3 = listOf(
      RecommendLink(1, "Title", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
      RecommendLink(2, "Category", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
      RecommendLink(3, "LeeSak", "www.naver.com", "https://avatars.githubusercontent.com/u/93514333?v=4"),
    )
    initView(list, list2, list3)
    navigateToSetting()
    navigateToSearch()
  }

  private fun initAdapter() {
    setClipAdapter()
    setWeekLinkAdapter()
    setWeekRecommendAdapter()
  }

  private fun initView(
    list: List<Category?>,
    list2: List<WeekBestLink>,
    list3: List<RecommendLink>,
  ) {
    collectClip(list)
    collectWeekLink(list2)
    collectRecommendLink(list3)
    binding.pbLinkmindHome.setProgressBarMain(54)
  }

  private fun navigateToSetting() {
    binding.ivHomeSetting.onThrottleClick {
      navigateToDestination("featureMyPage://fragmentSetting")
    }
  }

  private fun navigateToSearch() {
    binding.clHomeSearch.onThrottleClick {
      navigateToDestination("featureMyPage://fragmentSearch")
    }
  }

  private fun setClipAdapter() {
    homeClipAdapter = HomeClipAdapter(
      onClickClip = {},
      onClickEmptyClip = {
        showHomeBottomSheet()
      },
    )
    binding.rvHomeClip.adapter = homeClipAdapter
    val spacingClipInPixels = resources.getDimensionPixelSize(R.dimen.spacing_11)
    binding.rvHomeClip.addItemDecoration(ItemDecoration(2, spacingClipInPixels))
  }

  private fun setWeekLinkAdapter() {
    homeWeekLinkAdapter = HomeWeekLinkAdapter(onClickWeekLink = {})
    binding.rvWeekLink.adapter = homeWeekLinkAdapter
  }

  private fun setWeekRecommendAdapter() {
    homeWeekRecommendLinkAdapter = HomeWeekRecommendLinkAdapter(onClickRecommendLink = {})
    binding.rvHomeWeekRecommend.adapter = homeWeekRecommendLinkAdapter
    val spacingWeekRecommendInPixels = resources.getDimensionPixelSize(R.dimen.spacing_12)
    binding.rvHomeWeekRecommend.addItemDecoration(ItemDecoration(3, spacingWeekRecommendInPixels))
  }

  private fun collectRecommendLink(list2: List<RecommendLink>) {
    homeWeekRecommendLinkAdapter.submitList(list2)
  }

  private fun collectWeekLink(list2: List<WeekBestLink>) {
    homeWeekLinkAdapter.submitList(list2)
  }

  private fun collectClip(list: List<Category?>) {
    homeClipAdapter.submitList(list)
  }

  private fun navigateToDestination(destination: String) {
    val (request, navOptions) = DeepLinkUtil.getNavRequestNotPopUpAndOption(
      destination,
      enterAnim = org.sopt.mainfeature.R.anim.from_bottom,
      exitAnim = android.R.anim.fade_out,
      popEnterAnim = android.R.anim.fade_in,
      popExitAnim = org.sopt.mainfeature.R.anim.to_bottom,
    )
    findNavController().navigate(request, navOptions)
  }

  private fun showHomeBottomSheet() {
    val linkMindBottomSheet = LinkMindBottomSheet(requireContext())
    linkMindBottomSheet.show()
    linkMindBottomSheet.apply {
      setBottomSheetHint(org.sopt.mainfeature.R.string.home_new_clip_info)
      setTitle(org.sopt.mainfeature.R.string.home_correction_clip)
      setErroMsg(org.sopt.mainfeature.R.string.home_error_clip_info)
      bottomSheetConfirmBtnClick {
        if (showErrorMsg()) return@bottomSheetConfirmBtnClick
        dismiss()
        requireContext().linkMindSnackBar(binding.root, "성공", false)
      }
    }
  }
}
