package org.sopt.savelink.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.domain.category.category.usecase.GetCategoryAllUseCase
import org.sopt.domain.category.category.usecase.PostAddCategoryTitleUseCase
import org.sopt.domain.link.usecase.DeleteLinkUseCase
import org.sopt.domain.link.usecase.PatchReadLinkUseCase
import org.sopt.domain.link.usecase.PostSaveLinkUseCase
import javax.inject.Inject

@HiltViewModel
class SaveLinkViewModel @Inject constructor(
  private val deleteLinkUseCase: DeleteLinkUseCase,
  private val patchReadLinkUseCase: PatchReadLinkUseCase,
  private val saveLinkUseCase: PostSaveLinkUseCase,
  private val getCategoryAllUseCase: GetCategoryAllUseCase,
  private val postAddCategoryTitle: PostAddCategoryTitleUseCase,
) : ViewModel() {

  fun saveCategoryTitle(categoryTitle: String) = viewModelScope.launch {
    postAddCategoryTitle(
      PostAddCategoryTitleUseCase.Param(
        categoryTitle = categoryTitle
      ),
    ).onSuccess {
      Log.d("saveCategoryTitleSuccess", "$it")
    }.onFailure { Log.d("saveCategoryTitleFail", "$it") }
  }

  fun getCategortAll() = viewModelScope.launch {
    getCategoryAllUseCase(
    ).onSuccess {
      Log.d("getCategortSuccess", "$it")
    }.onFailure {

      Log.d("getCategortFail", "$it")
    }
  }

  fun saveLink(linkUrl: String, categoryId: Long?) = viewModelScope.launch {
    saveLinkUseCase(
      PostSaveLinkUseCase.Param(
        linkUrl = linkUrl, categoryId = categoryId,
      ),
    ).onSuccess {
      Log.d("saveLinkSuccess", "$it")
    }.onFailure { Log.d("SaveLinkFail", "$it") }
  }

  fun deleteLink(toastId: Long) = viewModelScope.launch {
    deleteLinkUseCase(
      DeleteLinkUseCase.Param(
        toastId = toastId,
      ),
    ).onSuccess {
      Log.d("deleteLinkSuccess", "$it")
    }.onFailure { Log.d("deleteLinkFail", "$it") }
  }

  fun patchReadLink(toastId: Long) = viewModelScope.launch {
    patchReadLinkUseCase(
      PatchReadLinkUseCase.Param(
        toastId = toastId,
      ),
    ).onSuccess { Log.d("patchReadLinkSuccess", "$it") }.onFailure { Log.d("patchReadLinkFail", "$it") }
  }
}
