package org.sopt.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.category.category.usecase.PostAddCategoryTitleUseCase
import org.sopt.home.usecase.GetMainPageUserClip
import org.sopt.home.usecase.GetRecommendSite
import org.sopt.home.usecase.GetWeekBestLink
import org.sopt.model.category.Category
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val getMainPageUserClip: GetMainPageUserClip,
  private val getRecommendSite: GetRecommendSite,
  private val getWeekBestLink: GetWeekBestLink,
  private val postAddCategoryTitle: PostAddCategoryTitleUseCase,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
  override val container: Container<HomeState, HomeSideEffect> =
    container(HomeState())

  fun saveCategoryTitle(categoryTitle: String) = viewModelScope.launch {
    postAddCategoryTitle(
      PostAddCategoryTitleUseCase.Param(
        categoryTitle = categoryTitle,
      ),
    ).onSuccess {
      getMainPageUserClip()
    }.onFailure { Log.d("saveCategoryTitleFail", "$it") }
  }

  fun getMainPageUserClip() = intent {
    getMainPageUserClip.invoke().onSuccess {
      reduce {
        state.copy(
          nickName = it.nickName,
          allToastNum = it.allToastNum,
          readToastNum = it.readToastNum,
          categoryList = (
            listOf(
              Category(
                0,
                "전체 카테고리",
                it.allToastNum,
              ),
            ) + container.stateFlow.value.categoryList + it.mainCategoryDto + null).distinctBy { it?.categoryId },
        )
      }
    }.onFailure {
      Log.d("MainUser", "$it")
    }
  }

  fun getRecommendSite() = intent {
    getRecommendSite.invoke().onSuccess {
      reduce {
        state.copy(recommendLink = (container.stateFlow.value.recommendLink + it).distinctBy { it.siteId })
      }
    }.onFailure {
      Log.d("Recommend", "$it")
    }
  }

  fun getWeekBestLink() = intent {
    getWeekBestLink.invoke().onSuccess {
      reduce {
        state.copy(weekBestLink = (container.stateFlow.value.weekBestLink + it).distinctBy { it.toastId })
      }
    }.onFailure {
      Log.d("getWeekBestLink", "$it")
    }
  }

  fun navigateSearch() = intent { postSideEffect(HomeSideEffect.NavigateSearch) }
  fun navigateSetting() = intent { postSideEffect(HomeSideEffect.NavigateSetting) }
  fun showBottomSheet() = intent { postSideEffect(HomeSideEffect.ShowBottomSheet) }

  @OptIn(OrbitExperimental::class)
  fun navigateClipLink(categoryId: Long?) = blockingIntent {
    reduce { state.copy(categoryId = categoryId) }
    postSideEffect(HomeSideEffect.NavigateClipLink)
  }

  @OptIn(OrbitExperimental::class)
  fun navigateWebview(url: String) = blockingIntent {
    reduce { state.copy(url = url) }
    postSideEffect(HomeSideEffect.NavigateWebView)
  }
}
