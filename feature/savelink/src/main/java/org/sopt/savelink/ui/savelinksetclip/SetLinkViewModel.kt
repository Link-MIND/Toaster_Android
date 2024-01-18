package org.sopt.savelink.ui.savelinksetclip

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
import org.sopt.savelink.ui.model.Clip
import org.sopt.savelink.ui.model.toModel
import javax.inject.Inject

@HiltViewModel
class SetLinkViewModel @Inject constructor(
  private val saveLinkUseCase: PostSaveLinkUseCase,
  private val getCategoryAllUseCase: GetCategoryAllUseCase,
  private val postAddCategoryTitle: PostAddCategoryTitleUseCase,
) : ContainerHost<SaveLinkSetClipState, SaveLinkSetClipSideEffect>, ViewModel() {
  override val container: Container<SaveLinkSetClipState, SaveLinkSetClipSideEffect> =
    container(SaveLinkSetClipState())

  fun getCategoryAll() = intent {
    getCategoryAllUseCase().onSuccess {
      reduce {
        state.copy(
          categoryList = (
            listOf(
              Clip(
                null,
                "전체 카테고리",
                it.toastNumberInEntire,
                false,
              ),
            ) + container.stateFlow.value.categoryList + it.categories.map { it.toModel() }
            ).distinctBy { it.categoryId },
        )
      }
    }.onFailure {
      Log.d("getCategortFail", "$it")
    }
  }

  fun saveCategoryTitle(categoryTitle: String) = viewModelScope.launch {
    postAddCategoryTitle(
      PostAddCategoryTitleUseCase.Param(
        categoryTitle = categoryTitle,
      ),
    ).onSuccess {
      getCategoryAll()
    }.onFailure { Log.d("saveCategoryTitleFail", "$it") }
  }

  fun saveLink(linkUrl: String, categoryId: Long?) = viewModelScope.launch {
    saveLinkUseCase(
      PostSaveLinkUseCase.Param(
        linkUrl = linkUrl,
        categoryId = categoryId,
      ),
    ).onSuccess {
      Log.d("save", "$it")
      navigateSetLink()
      if (it != 201) showSnackBar()
    }.onFailure {
      showSnackBar()
    }
  }

  fun updateCategoryId(categoryId: Long?) = intent {
    reduce {
      state.copy(categoryId = categoryId)
    }
  }

  fun updateUrl(url: String) = intent {
    reduce {
      state.copy(url = url)
    }
  }

  private fun navigateSetLink() = intent { postSideEffect(SaveLinkSetClipSideEffect.NavigateSaveLinkSetClip) }
  fun navigateUp() = intent { postSideEffect(SaveLinkSetClipSideEffect.NavigateUp) }
  fun showBottomSheet() = intent { postSideEffect(SaveLinkSetClipSideEffect.ShowBottomSheet) }
  fun showDialog() = intent { postSideEffect(SaveLinkSetClipSideEffect.ShowDialog) }

  private fun showSnackBar() = intent { postSideEffect(SaveLinkSetClipSideEffect.ShowSnackBar) }
}
