package org.sopt.timer.usecase

import org.sopt.model.timer.Repeat
import javax.inject.Inject

class FormatRepeatListToIntList @Inject constructor() {
  operator fun invoke(repeatList: List<Repeat>): List<Int> {
    val selectedDays = mutableSetOf<Int>()

    repeatList.forEachIndexed { index, repeat ->
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
