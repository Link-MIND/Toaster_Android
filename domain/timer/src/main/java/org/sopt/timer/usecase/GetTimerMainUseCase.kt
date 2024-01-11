package org.sopt.timer.usecase

import org.sopt.timer.repository.TimerRepository
import javax.inject.Inject

class GetTimerMainUseCase @Inject constructor(
  private val timerRepository: TimerRepository
) {
  suspend operator fun invoke() = timerRepository.getTimerMain()
}
