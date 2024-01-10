package org.sopt.timer.settimer

sealed interface TimerUiState<out T> {
  data class BothAllowed<T>(
    val data: T
  ) : TimerUiState<T>
  object AppAllowed : TimerUiState<Nothing>

  object DeviceAllowed : TimerUiState<Nothing>

  object NotAllowed : TimerUiState<Nothing>

  object Loading : TimerUiState<Nothing>

  object Empty : TimerUiState<Nothing>
}
