package org.sopt.mypage.my

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.model.user.MyPageData
import org.sopt.ui.view.UiState
import org.sopt.user.usecase.GetUserMyPageUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
  private val getUserMyPageUseCase: GetUserMyPageUseCase,
) : ViewModel() {
  private val _myPageState = MutableStateFlow<UiState<MyPageData>>(UiState.Empty)
  val myPageState: StateFlow<UiState<MyPageData>> = _myPageState.asStateFlow()
  fun getUserMyPage() = viewModelScope.launch {
    getUserMyPageUseCase.invoke().onSuccess { data ->
      _myPageState.emit(UiState.Success(data))
    }.onFailure { error ->
      _myPageState.emit(UiState.Failure(error.toString()))
    }
  }
}
