package org.sopt.clip

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.domain.category.category.usecase.DeleteCategoryUseCase
import org.sopt.domain.category.category.usecase.GetCategoryAllUseCase
import org.sopt.domain.category.category.usecase.GetCategoryDuplicateUseCase
import org.sopt.domain.category.category.usecase.GetCategoryLinkUseCase
import org.sopt.domain.category.category.usecase.PatchCategoryEditPriorityUseCase
import org.sopt.domain.category.category.usecase.PatchCategoryEditTitleUseCase
import org.sopt.domain.category.category.usecase.PostAddCategoryTitleUseCase
import org.sopt.domain.link.usecase.DeleteLinkUseCase
import org.sopt.model.category.Category
import org.sopt.model.category.CategoryDuplicate
import org.sopt.model.category.CategoryLink
import org.sopt.ui.view.UiState
import javax.inject.Inject

@HiltViewModel
class ClipViewModel @Inject constructor(
  private val getCategoryAll: GetCategoryAllUseCase,
  private val deleteCategory: DeleteCategoryUseCase,
  private val getCategoryDuplicate: GetCategoryDuplicateUseCase,
  private val getCategoryLink: GetCategoryLinkUseCase,
  private val postAddCategoryTitle: PostAddCategoryTitleUseCase,
  private val patchCategoryEditTitle: PatchCategoryEditTitleUseCase,
  private val patchCategoryEditPriority: PatchCategoryEditPriorityUseCase,
  private val deleteLinkUseCase: DeleteLinkUseCase,
) : ViewModel() {
  private val _categoryState = MutableStateFlow<UiState<List<Category>>>(UiState.Empty)
  val categoryState: StateFlow<UiState<List<Category>>> = _categoryState.asStateFlow()

  private val _linkState = MutableStateFlow<UiState<List<CategoryLink>>>(UiState.Empty)
  val linkState: StateFlow<UiState<List<CategoryLink>>> = _linkState.asStateFlow()

  private val _duplicateState = MutableStateFlow<UiState<CategoryDuplicate>>(UiState.Empty)
  val duplicateState: StateFlow<UiState<CategoryDuplicate>> = _duplicateState.asStateFlow()

  private val _categoryDeleteState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
  val categoryDeleteState: StateFlow<UiState<Unit>> = _categoryDeleteState.asStateFlow()

  private val _editTitleState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
  val editTitleState: StateFlow<UiState<Unit>> = _editTitleState.asStateFlow()

  private val _editPriorityState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
  val editPriorityState: StateFlow<UiState<Unit>> = _editPriorityState.asStateFlow()

  private val _deleteState = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
  val deleteState: StateFlow<UiState<Boolean>> = _deleteState.asStateFlow()

  private val _last2 = MutableStateFlow<UiState<Int>>(UiState.Empty)
  val last2: StateFlow<UiState<Int>> = _last2.asStateFlow()

  private val _allClipCount = MutableStateFlow<UiState<Long>>(UiState.Empty)
  val allClipCount: StateFlow<UiState<Long>> = _allClipCount.asStateFlow()

  fun update2(a: Int) = viewModelScope.launch {
    _last2.emit(UiState.Success(a))
  }

  init {
    getCategoryAll()
  }

  var toggleSelectedPast: SelectedToggle = SelectedToggle.ALL
  fun deleteLink(toastId: Long) = viewModelScope.launch {
    deleteLinkUseCase.invoke(param = DeleteLinkUseCase.Param(toastId = toastId)).onSuccess {
      if (it == 200) {
        _deleteState.emit(UiState.Success(true))
      } else {
        _deleteState.emit(UiState.Success(false))
      }
    }.onFailure {
      _deleteState.emit(UiState.Failure("fail"))
    }
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

  fun deleteCategory(deleteCategoryList: Long) = viewModelScope.launch {
    deleteCategory.invoke(param = DeleteCategoryUseCase.Param(deleteCategoryList)).onSuccess {
      _categoryDeleteState.emit(UiState.Success(it))
      getCategoryAll()
    }.onFailure {
      Log.d("카테 삭제-함수안", "실패 $it")
    }
  }

  // 서버 통신 확인 완
  fun getCategoryDuplicate(title: String) = viewModelScope.launch {
    getCategoryDuplicate.invoke(param = GetCategoryDuplicateUseCase.Param(title)).onSuccess {
      _duplicateState.emit(UiState.Success(it))
      if (it.isDuplicate) return@launch
      postAddCategoryTitle(title)
    }.onFailure {
      Log.d("카테 중복 체크", "실패 $it")
    }
  }

  fun getCategoryLink(filter: String?, categoryId: Long?) = viewModelScope.launch {
    getCategoryLink(param = GetCategoryLinkUseCase.Param(filter = filter, categoryId = categoryId)).onSuccess {
      val list: MutableList<CategoryLink> = it.toastListDto.toMutableList()
      _linkState.emit(UiState.Success(list))
    }.onFailure {
      Log.d("카테 안의 링크 검색", it.message.toString())
    }
  }

  // 확인 완
  fun postAddCategoryTitle(categoryTitle: String) = viewModelScope.launch {
    postAddCategoryTitle.invoke(param = PostAddCategoryTitleUseCase.Param(categoryTitle)).onSuccess {
      getCategoryAll()
    }.onFailure {
      Log.d("카테 추가", "실패")
    }
  }

  fun patchCategoryEditTitle(categoryId: Long, newTitle: String) = viewModelScope.launch {
    patchCategoryEditTitle.invoke(param = PatchCategoryEditTitleUseCase.Param(categoryId, newTitle)).onSuccess {
      _editTitleState.emit(UiState.Success(Unit))
    }.onFailure {
      Log.d("카테 이름 수정", "실패 $it")
    }
  }

  fun patchCategoryEditPriority(categoryId: Long, newPriority: Int) = viewModelScope.launch {
    patchCategoryEditPriority.invoke(param = PatchCategoryEditPriorityUseCase.Param(categoryId, newPriority)).onSuccess {
      _editPriorityState.emit(UiState.Success(Unit))
      getCategoryAll()
    }.onFailure {
      Log.d("순위 변경", "실패 $it")
    }
  }
}
