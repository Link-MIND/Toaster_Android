package org.sopt.timer.model.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPatchTimerDto(
  @SerialName("remindTime")
  val remindTime: String,
  @SerialName("remindDates")
  val remindDates: List<Int>,
)
