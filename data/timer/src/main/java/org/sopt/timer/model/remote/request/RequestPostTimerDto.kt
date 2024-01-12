package org.sopt.timer.model.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.timer.TimerData

@Serializable
data class RequestPostTimerDto(
  @SerialName("categoryId")
  val categoryId: Long,
  @SerialName("remindTime")
  val remindTime: String,
  @SerialName("remindDates")
  val remindDates: List<Int>,
)

fun TimerData.toDataModel() = RequestPostTimerDto(
  categoryId = this.categoryId,
  remindTime = this.remindTime,
  remindDates = this.remindDates,
)
