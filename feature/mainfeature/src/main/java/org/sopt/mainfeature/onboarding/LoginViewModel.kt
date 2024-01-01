package org.sopt.mainfeature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.auth.model.Auth
import org.sopt.auth.model.UserData
import org.sopt.auth.repository.AuthRepository
import org.sopt.ui.view.UiState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val authRepository: AuthRepository,
) : ViewModel() {
  private val _authState = MutableStateFlow<UiState<UserData>>(UiState.Empty)
  val authState: StateFlow<UiState<UserData>> = _authState.asStateFlow()

  fun authenticate(socialToken: String, socialType: String) {
    viewModelScope.launch {
      _authState.emit(UiState.Loading)
      val auth = Auth(
        socialToken,
        authRepository.getToken().deviceToken,
        socialType,
      )
      authRepository.authenticate(auth)
        .onSuccess {
          if(it == null) return@launch
          authRepository.saveToken(it.first)
          _authState.emit(UiState.Success(it.second))
        }.onFailure {
          _authState.emit(UiState.Failure(it.message.toString()))
        }
    }
  }
}
