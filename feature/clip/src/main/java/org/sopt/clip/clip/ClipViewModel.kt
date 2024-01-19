package org.sopt.clip.clip

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.domain.category.category.usecase.GetCategoryAllUseCase
import org.sopt.domain.category.category.usecase.GetCategoryDuplicateUseCase
import org.sopt.domain.category.category.usecase.PostAddCategoryTitleUseCase
import org.sopt.model.category.Category
import org.sopt.model.category.CategoryDuplicate
import org.sopt.ui.view.UiState
import javax.inject.Inject

@HiltViewModel
class ClipViewModel @Inject constructor(
  private val getCategoryAll: GetCategoryAllUseCase,
  private val getCategoryDuplicate: GetCategoryDuplicateUseCase,
  private val postAddCategoryTitle: PostAddCategoryTitleUseCase,
) : ViewModel() {
  private val _categoryState = MutableStateFlow<UiState<List<Category>>>(UiState.Empty)
  val categoryState: StateFlow<UiState<List<Category>>> = _categoryState.asStateFlow()

  private val _duplicateState = MutableStateFlow<UiState<CategoryDuplicate>>(UiState.Empty)
  val duplicateState: StateFlow<UiState<CategoryDuplicate>> = _duplicateState.asStateFlow()

  private val _allClipCount = MutableStateFlow<UiState<Long>>(UiState.Empty)
  val allClipCount: StateFlow<UiState<Long>> = _allClipCount.asStateFlow()

  private val _test = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
  val test: StateFlow<UiState<Boolean>> = _test.asStateFlow()
  init {
    getCategoryAll()
  }

  fun getCategoryAll() = viewModelScope.launch {
    getCategoryAll.invoke().onSuccess {
      val allCategoryList = listOf<Category>(
        Category(0, "전체 클립", it.toastNumberInEntire),
      )
      _allClipCount.emit(UiState.Success(it.toastNumberInEntire))
      _categoryState.emit(UiState.Success(allCategoryList + it.categories))
    }.onFailure {
      Log.e("실패", it.message.toString())
    }
  }
  fun updateDuplicateState() = viewModelScope.launch {
    _duplicateState.emit(UiState.Success(CategoryDuplicate(false)))
  }
  fun getCategoryDuplicate(title: String) = viewModelScope.launch {
    getCategoryDuplicate.invoke(param = GetCategoryDuplicateUseCase.Param(title)).onSuccess {
      Log.d("test", "$it")
      if (it.isDuplicate) {
        _duplicateState.emit(UiState.Success(it))
        return@launch
      }
      _duplicateState.emit(UiState.Success(it))
      postAddCategoryTitle(title)
    }.onFailure {
      Log.d("test2", "$it")
    }
  }

  fun postAddCategoryTitle(categoryTitle: String) = viewModelScope.launch {
    postAddCategoryTitle.invoke(param = PostAddCategoryTitleUseCase.Param(categoryTitle)).onSuccess {
      getCategoryAll()
      _test.emit(UiState.Success(true))
    }.onFailure {
      _test.emit(UiState.Success(false))
      Log.d("카테 추가", "실패")
    }
  }
}
