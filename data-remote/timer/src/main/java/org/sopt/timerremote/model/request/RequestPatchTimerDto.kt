package org.sopt.timerremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.timer.TimerData

@Serializable
data class RequestPatchTimerDto(
  @SerialName("remindTime")
  val remindTime: String,
  @SerialName("remindDates")
  val remindDates: List<Int>,
)

fun TimerData.toPatchDto() = RequestPatchTimerDto(
  remindTime = this.remindTime,
  remindDates = this.remindDates,
)
