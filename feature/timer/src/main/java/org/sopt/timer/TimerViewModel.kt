package org.sopt.timer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.model.timer.Timer
import org.sopt.timer.settimer.TimerUiState
import org.sopt.timer.usecase.DeleteTimerUseCase
import org.sopt.timer.usecase.GetTimerMainUseCase
import org.sopt.timer.usecase.PatchAlarmUseCase
import org.sopt.ui.view.UiState
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
  private val getTimerMainUseCase: GetTimerMainUseCase,
  private val deleteTimerUseCase: DeleteTimerUseCase,
  private val patchAlarmUseCase: PatchAlarmUseCase,
) : ViewModel() {
  private val _uiState = MutableStateFlow<TimerUiState<Pair<List<Timer>, List<Timer>>?>>(TimerUiState.Empty)
  val uiState: StateFlow<TimerUiState<Pair<List<Timer>, List<Timer>>?>> get() = _uiState

  private val fcmIsAllowed = MutableStateFlow(true)

  val timerList = MutableStateFlow<Pair<List<Timer>, List<Timer>>?>(Pair(emptyList(), emptyList()))

  private val _deleteState = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
  val deleteState: StateFlow<UiState<Boolean>> = _deleteState.asStateFlow()

  fun setUiState(isNotiPermissionAllowed: Boolean) {
    viewModelScope.launch {
      when {
        fcmIsAllowed.value && isNotiPermissionAllowed -> {
          _uiState.emit(TimerUiState.BothAllowed(timerList.value))
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

  fun getTimerMain() {
    if (uiState.value !is TimerUiState.BothAllowed) return
    viewModelScope.launch {
      getTimerMainUseCase().onSuccess {
        _uiState.emit(TimerUiState.BothAllowed(it))
        timerList.emit(it)
      }.onFailure {
        Log.e("실패", it.message.toString())
        _uiState.emit(TimerUiState.BothAllowed(null))
      }
    }
  }

  fun deleteTimer(timerId: Int) = viewModelScope.launch {
    deleteTimerUseCase(timerId).onSuccess {
      _deleteState.emit(UiState.Success(true))
      _deleteState.emit(UiState.Empty)
      getTimerMain()
    }.onFailure {
      Log.e("실패", it.message.toString())
      _deleteState.emit(UiState.Failure(it.message.toString()))
      _deleteState.emit(UiState.Empty)
    }
  }

  fun patchAlarm(timerId: Int) = viewModelScope.launch {
    patchAlarmUseCase(timerId).onSuccess {
      getTimerMain()
    }.onFailure {
      Log.e("실패", it.message.toString())
    }
  }
}
