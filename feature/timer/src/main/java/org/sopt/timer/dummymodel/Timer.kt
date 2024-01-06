package org.sopt.timer.dummymodel

data class Timer(
  val id: Int,
  val category: String,
  val day: String,
  val am: Boolean,
  val hour: Int,
  val minute: Int,
)
