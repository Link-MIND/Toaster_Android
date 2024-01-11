package org.sopt.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.timer.dummymodel.Timer
import org.sopt.timer.settimer.TimerUiState
import org.sopt.timer.usecase.GetTimerMainUseCase
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
  private val getTimerMainUseCase: GetTimerMainUseCase
): ViewModel() {
  private val _uiState = MutableStateFlow<TimerUiState<List<Timer>>>(TimerUiState.Empty)
  val uiState: StateFlow<TimerUiState<List<Timer>>> get() = _uiState

  private val fcmIsAllowed = MutableStateFlow(true)

  private val completeTimerList = MutableStateFlow<List<Timer>>(
    listOf(
      Timer(1, "네이버", "일요일", true, 8, 37),
      Timer(1, "네이버", "일요일", true, 8, 37),
    ),
  )

  fun setUiState(isNotiPermissionAllowed: Boolean) {
    viewModelScope.launch {
      when {
        fcmIsAllowed.value && isNotiPermissionAllowed -> {
          _uiState.emit(TimerUiState.BothAllowed(completeTimerList.value))
          return@launch
        }
        fcmIsAllowed.value -> {
          _uiState.emit(TimerUiState.AppAllowed)
          return@launch
        }
        isNotiPermissionAllowed -> {
          _uiState.emit(TimerUiState.DeviceAllowed)
          return@launch
        }
      }
      _uiState.emit(TimerUiState.NotAllowed)
    }
  }
}
