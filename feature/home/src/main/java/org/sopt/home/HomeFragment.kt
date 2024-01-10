package org.sopt.home

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import coil.network.HttpException
import designsystem.components.bottomsheet.LinkMindBottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.sopt.home.adapter.HomeClipAdapter
import org.sopt.home.adapter.HomeWeekLinkAdapter
import org.sopt.home.adapter.HomeWeekRecommendLinkAdapter
import org.sopt.home.databinding.FragmentHomeBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick
import java.io.IOException

class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {

  private lateinit var homeClipAdapter: HomeClipAdapter
  private lateinit var homeWeekLinkAdapter: HomeWeekLinkAdapter
  private lateinit var homeWeekRecommendLinkAdapter: HomeWeekRecommendLinkAdapter
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    fetchWebContent()
    binding.clHomeSearch.onThrottleClick {

    }
    initAdapter()
    val list = listOf(
      ClipDummy("전체클립", 1),
      ClipDummy("TitleCheck", 1),
      ClipDummy("LeeSak", 3),
      null,
    )

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

  fun fetchWebContent() {
    CoroutineScope(Dispatchers.Main).launch {
      val result = withContext(Dispatchers.IO) {
        val url = "https://www.figma.com/file/3PL8DmNIAOjJAErrb3I8JT/%ED%86%A0%EC%8A%A4%ED%84%B0-%EB%94%94%EC%9E%90%EC%9D%B8?node-id=400%3A936&mode=devhjvhjvhjhvhvuhvuvyuvuyvukjWEFkjskjlbjkldfskbjakjsfdnkdnsjonwjwkjwdjn"
        try {
          val document = Jsoup.connect(url).get()
          val content = document.select("title")
          content.map { it.text() }
        } catch (e: HttpException) {
          // 서버에서 HTTP 오류를 반환할 경우 (예: 404, 500 등)
          Log.e("test", "HTTP 오류: ${e.response.body.toString()}}")
          null
        } catch (e: IOException) {
          // 네트워크 오류, 인증 오류, 리디렉션을 찾을 수 없을 때 등등
          Log.e("test1", "입출력 오류: ${e.message}")
          null
        } catch (e: Exception) {
          // 그 외 모든 예외
          Log.e("test2", "기타 오류: ${e.message}")
          null
        }
      }
      result?.forEach { text ->
        Log.d("test", "$text")
      }
    }
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
