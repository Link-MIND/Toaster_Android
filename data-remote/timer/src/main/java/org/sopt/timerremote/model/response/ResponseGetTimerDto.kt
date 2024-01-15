package org.sopt.timerremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetTimerDto(
  @SerialName("categoryName")
  val categoryName: String,
  @SerialName("remindTime")
  val remindTime: String,
  @SerialName("remindDates")
  val remindDates: List<Int>,
)
