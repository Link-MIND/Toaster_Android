package org.sopt.clip.cliplink

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.clip.SelectedToggle
import org.sopt.domain.category.category.usecase.GetCategoryLinkUseCase
import org.sopt.domain.link.usecase.DeleteLinkUseCase
import org.sopt.domain.link.usecase.PatchLinkTitleUseCase
import org.sopt.model.category.CategoryLink
import org.sopt.ui.view.UiState
import javax.inject.Inject

@HiltViewModel
class ClipLinkViewModel @Inject constructor(
  private val getCategoryLink: GetCategoryLinkUseCase,
  private val deleteLinkUseCase: DeleteLinkUseCase,
  private val patchLinkTitleUseCase: PatchLinkTitleUseCase,
) : ViewModel() {
  private val _linkState = MutableStateFlow<UiState<List<CategoryLink>>>(UiState.Empty)
  val linkState: StateFlow<UiState<List<CategoryLink>>> = _linkState.asStateFlow()

  private val _deleteState = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
  val deleteState: StateFlow<UiState<Boolean>> = _deleteState.asStateFlow()

  private val _allClipCount = MutableStateFlow<UiState<Long>>(UiState.Empty)
  val allClipCount: StateFlow<UiState<Long>> = _allClipCount.asStateFlow()

  private val _patchLinkTitle = MutableStateFlow<UiState<String>>(UiState.Empty)
  val patchLinkTitle: StateFlow<UiState<String>> = _patchLinkTitle.asStateFlow()

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

  fun updateDeleteState() = viewModelScope.launch {
    _deleteState.emit(UiState.Success(false))
  }
  fun getCategoryLink(filter: String?, categoryId: Long?) = viewModelScope.launch {
    getCategoryLink(param = GetCategoryLinkUseCase.Param(filter = filter, categoryId = categoryId)).onSuccess {
      val list: MutableList<CategoryLink> = it.toastListDto.toMutableList()
      _allClipCount.emit(UiState.Success(it.allToastNum))
      _linkState.emit(UiState.Success(list))
    }.onFailure {
      Log.d("카테 안의 링크 검색", it.message.toString())
    }
  }

  fun patchLinkTitle(toastId: Long, title: String) = viewModelScope.launch {
    patchLinkTitleUseCase(param = PatchLinkTitleUseCase.Param(toastId = toastId, title = title)).onSuccess {
      _patchLinkTitle.emit(UiState.Success(it))
    }.onFailure {
      _patchLinkTitle.emit(UiState.Failure("fail"))
    }
  }

  fun initState() {
    _linkState.value = UiState.Empty
  }
}
