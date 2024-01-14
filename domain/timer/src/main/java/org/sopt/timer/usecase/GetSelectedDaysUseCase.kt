package org.sopt.timer.usecase

import org.sopt.model.timer.Repeat
import javax.inject.Inject

class GetSelectedDaysUseCase @Inject constructor() {
  operator fun invoke(repeatList: List<Repeat>): List<String> {
    val selectedDays = mutableSetOf<String>()
    val order = listOf("월", "화", "수", "목", "금", "토", "일")

    repeatList.forEach { repeat ->
      if (repeat.isSelected) {
        when (repeat.period) {
          "매일 (월~일)" -> selectedDays.addAll(listOf("월", "화", "수", "목", "금", "토", "일"))
          "주중마다 (월~금)" -> selectedDays.addAll(listOf("월", "화", "수", "목", "금"))
          "주말마다 (토~일)" -> selectedDays.addAll(listOf("토", "일"))
          "월요일마다" -> selectedDays.add("월")
          "화요일마다" -> selectedDays.add("화")
          "수요일마다" -> selectedDays.add("수")
          "목요일마다" -> selectedDays.add("목")
          "금요일마다" -> selectedDays.add("금")
          "토요일마다" -> selectedDays.add("토")
          "일요일마다" -> selectedDays.add("일")
        }
      }
    }
    return selectedDays.toList().sortedBy { day -> order.indexOf(day) }
  }
}
