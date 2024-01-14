package org.sopt.timer.usecase

import org.sopt.timer.repository.TimerRepository
import javax.inject.Inject

class DeleteTimerUseCase @Inject constructor(
  private val timerRepository: TimerRepository
) {
  suspend operator fun invoke(timerId: Int) = timerRepository.deleteTimer(timerId)
}
