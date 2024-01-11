package org.sopt.model.timer

data class Timer(
  val id: Int,
  val categoryId: Int,
  val remindTime: String,
  val remindDate: String?,
  val remindDates: String?,
  val comment: String?,
  val isAlarm: Boolean?
)
