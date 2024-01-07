package org.sopt.mypage.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.auth.repository.AuthRepository
import org.sopt.ui.view.UiState
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
  private val authRepository: AuthRepository,
) : ViewModel() {
  private val _logoutState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
  val logoutState: StateFlow<UiState<Unit>> = _logoutState.asStateFlow()

  private val _withdrawState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
  val withdrawState: StateFlow<UiState<Unit>> = _withdrawState.asStateFlow()

  fun logout() = viewModelScope.launch {
    authRepository.signout().onSuccess {
      Log.e("로그아웃성공", "로그아웃성공")
      _logoutState.emit(UiState.Success(it))
    }.onFailure {
      Log.e("로그아웃실패", "${it.message}")
      _logoutState.emit(UiState.Failure(it.message.toString()))
    }
  }

  fun withdraw() = viewModelScope.launch {
    authRepository.signout().onSuccess {
      _withdrawState.emit(UiState.Success(it))
    }.onFailure {
      _withdrawState.emit(UiState.Failure(it.message.toString()))
    }
  }
}
