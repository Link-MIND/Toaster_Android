package org.sopt.savelink.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.category.category.usecase.GetCategoryAllUseCase
import org.sopt.domain.category.category.usecase.PostAddCategoryTitleUseCase
import org.sopt.domain.link.usecase.PostSaveLinkUseCase
import javax.inject.Inject

@HiltViewModel
class SaveLinkViewModel @Inject constructor(
  private val saveLinkUseCase: PostSaveLinkUseCase,
  private val getCategoryAllUseCase: GetCategoryAllUseCase,
  private val postAddCategoryTitle: PostAddCategoryTitleUseCase,
) : ContainerHost<LinkState,LinkSideEffect>,ViewModel() {
  override val container: Container<LinkState, LinkSideEffect> =
    container(LinkState())
  fun saveCategoryTitle(categoryTitle: String) = viewModelScope.launch {
    postAddCategoryTitle(
      PostAddCategoryTitleUseCase.Param(
        categoryTitle = categoryTitle,
      ),
    ).onSuccess {
      Log.d("saveCategoryTitleSuccess", "$it")
    }.onFailure { Log.d("saveCategoryTitleFail", "$it") }
  }

  fun getCategortAll() = viewModelScope.launch {
    getCategoryAllUseCase().onSuccess {
      Log.d("getCategortSuccess", "$it")
    }.onFailure {
      Log.d("getCategortFail", "$it")
    }
  }

  fun saveLink(linkUrl: String, categoryId: Long?) = viewModelScope.launch {
    saveLinkUseCase(
      PostSaveLinkUseCase.Param(
        linkUrl = linkUrl,
        categoryId = categoryId,
      ),
    ).onSuccess {
      Log.d("saveLinkSuccess", "$it")
    }.onFailure { Log.d("SaveLinkFail", "$it") }
  }

  fun navigateUp() = intent { postSideEffect(LinkSideEffect.NavigateUp) }
  fun navigateSetLink() = intent { postSideEffect(LinkSideEffect.NavigateSetLink) }
  fun showBottomSheet() = intent { postSideEffect(LinkSideEffect.ShowBottomSheet) }

}
