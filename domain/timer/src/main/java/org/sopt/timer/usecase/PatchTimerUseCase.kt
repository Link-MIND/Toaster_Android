package org.sopt.timer.usecase

import org.sopt.model.timer.TimerData
import org.sopt.timer.repository.TimerRepository
import javax.inject.Inject

class PatchTimerUseCase @Inject constructor(
  private val timerRepository: TimerRepository,
) {
  suspend operator fun invoke(timerId: Int, time: String, days: List<Int>) =
    timerRepository.patchTimer(timerId, TimerData(0, time, days))
}
