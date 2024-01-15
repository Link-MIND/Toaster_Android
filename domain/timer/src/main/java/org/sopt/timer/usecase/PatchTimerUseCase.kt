package org.sopt.timer.usecase

import org.sopt.model.timer.Repeat
import org.sopt.model.timer.TimerData
import org.sopt.timer.repository.TimerRepository
import javax.inject.Inject

class PatchTimerUseCase @Inject constructor(
  private val timerRepository: TimerRepository,
) {
  suspend operator fun invoke(timerId: Int, time: String, days: List<Repeat>) =
    timerRepository.patchTimer(timerId, TimerData(0, time, days.getSelectedDays()))

  private fun List<Repeat>.getSelectedDays(): List<Int> {
    val selectedDays = mutableSetOf<Int>()

    this.forEachIndexed { index, repeat ->
      if (repeat.isSelected) {
        when (index) {
          0 -> return (1..7).toList()
          1 -> selectedDays.addAll(1..5)
          2 -> selectedDays.addAll(listOf(6, 7))
          else -> selectedDays.add(index - 2)
        }
      }
    }
    return selectedDays.sorted()
  }
}
