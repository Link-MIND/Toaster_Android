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
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.category.category.usecase.GetCategoryAllUseCase
import org.sopt.domain.category.category.usecase.PostAddCategoryTitleUseCase
import org.sopt.domain.link.usecase.PostSaveLinkUseCase
import javax.inject.Inject

@HiltViewModel
class SetLinkViewModel @Inject constructor(
  private val saveLinkUseCase: PostSaveLinkUseCase,
  private val getCategoryAllUseCase: GetCategoryAllUseCase,
  private val postAddCategoryTitle: PostAddCategoryTitleUseCase,
) : ContainerHost<SetLinkState, SetLinkSideEffect>, ViewModel() {
  override val container: Container<SetLinkState, SetLinkSideEffect> =
    container(SetLinkState())
  fun saveCategoryTitle(categoryTitle: String) = viewModelScope.launch {
    postAddCategoryTitle(
      PostAddCategoryTitleUseCase.Param(
        categoryTitle = categoryTitle,
      ),
    ).onSuccess {
      Log.d("saveCategoryTitleSuccess", "$it")
    }.onFailure { Log.d("saveCategoryTitleFail", "$it") }
  }

  fun getCategortAll() = intent {
    getCategoryAllUseCase().onSuccess {
      reduce {
        state.copy(
          categoryList = it.categories.map { it.toModel() }
        )
      }
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
      navigateSetLink()
    }.onFailure { Log.d("SaveLinkFail", "$it") }
  }

  fun updateCategoryId(categoryId:Long) = intent {
    reduce {
      state.copy(categoryId = categoryId)
    }
  }

  private fun navigateSetLink() = intent { postSideEffect(SetLinkSideEffect.NavigateSetLink) }
  fun showBottomSheet() = intent { postSideEffect(SetLinkSideEffect.ShowBottomSheet) }
  fun showDialog() = intent { postSideEffect(SetLinkSideEffect.ShowDialog) }

}
