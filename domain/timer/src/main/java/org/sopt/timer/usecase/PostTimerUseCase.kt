package org.sopt.timer.usecase

import org.sopt.model.timer.TimerData
import org.sopt.timer.repository.TimerRepository
import javax.inject.Inject

class PostTimerUseCase @Inject constructor(
  private val timerRepository: TimerRepository,
) {
  suspend operator fun invoke(categoryId: Long, time: String, days: List<Int>) =
    timerRepository.postTimer(TimerData(categoryId, time, days))
}
