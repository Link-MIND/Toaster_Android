package org.sopt.model.timer

data class TimerData(
  val categoryId: Long?,
  val remindTime: String,
  val remindDates: List<Int>,
)
