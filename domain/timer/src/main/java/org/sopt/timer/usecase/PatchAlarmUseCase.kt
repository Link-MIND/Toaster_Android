package org.sopt.timer.usecase

import org.sopt.timer.repository.TimerRepository
import javax.inject.Inject

class PatchAlarmUseCase @Inject constructor(
  private val timerRepository: TimerRepository,
) {
  suspend operator fun invoke(timerId: Int) = timerRepository.patchAlarm(timerId)
}
