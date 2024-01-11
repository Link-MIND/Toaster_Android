package org.sopt.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.timer.dummymodel.Timer
import org.sopt.timer.settimer.TimerUiState

class TimerViewModel : ViewModel() {
  private val _uiState = MutableStateFlow<TimerUiState<List<Timer>>>(TimerUiState.Empty)
  val uiState: StateFlow<TimerUiState<List<Timer>>> get() = _uiState

  private val fcmIsAllowed = MutableStateFlow(false)

  private val timerList = MutableStateFlow<List<Timer>>(
    listOf(
      Timer(1, "네이버", "일요일", true, 8, 37),
      Timer(1, "네이버", "일요일", true, 8, 37),
    ),
  )

  fun setUiState(isNotiPermissionAllowed: Boolean) {
    viewModelScope.launch {
      if (fcmIsAllowed.value && isNotiPermissionAllowed) {
        _uiState.emit(TimerUiState.BothAllowed(timerList.value))
      } else if (fcmIsAllowed.value && !isNotiPermissionAllowed) {
        _uiState.emit(TimerUiState.AppAllowed)
      } else if (!fcmIsAllowed.value && isNotiPermissionAllowed) {
        _uiState.emit(TimerUiState.DeviceAllowed)
      } else {
        _uiState.emit(TimerUiState.NotAllowed)
      }
    }
  }
}
