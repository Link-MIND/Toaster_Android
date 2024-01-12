package org.sopt.timer.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.timer.Timer

@Serializable
data class ResponseGetTimerPageDto(
  @SerialName("completedTimerList")
  val completedTimerList: List<CompletedTimer>,
  @SerialName("waitingTimerList")
  val waitingTimerList: List<WaitingTimer>,
) {
  @Serializable
  data class CompletedTimer(
    @SerialName("categoryId")
    val categoryId: Int,
    @SerialName("comment")
    val comment: String,
    @SerialName("remindDate")
    val remindDate: String,
    @SerialName("remindTime")
    val remindTime: String,
    @SerialName("timerId")
    val timerId: Int,
    @SerialName("updateAt")
    val updateAt: String,
  )

  @Serializable
  data class WaitingTimer(
    @SerialName("categoryId")
    val categoryId: Int,
    @SerialName("isAlarm")
    val isAlarm: Boolean,
    @SerialName("remindDates")
    val remindDates: String,
    @SerialName("remindTime")
    val remindTime: String,
    @SerialName("timerId")
    val timerId: Int,
    @SerialName("comment")
    val comment: String,
  )
}

fun ResponseGetTimerPageDto.toCoreModel(): Pair<List<Timer>, List<Timer>> {
  val completedTimers = completedTimerList.map { completedTimer ->
    Timer(
      id = completedTimer.timerId,
      categoryId = completedTimer.categoryId,
      remindTime = completedTimer.remindTime,
      remindDate = completedTimer.remindDate,
      remindDates = null,
      comment = completedTimer.comment,
      isAlarm = null,
    )
  }

  val waitingTimers = waitingTimerList.map { waitingTimer ->
    Timer(
      id = waitingTimer.timerId,
      categoryId = waitingTimer.categoryId,
      remindTime = waitingTimer.remindTime,
      remindDate = null,
      remindDates = waitingTimer.remindDates,
      comment = waitingTimer.comment,
      isAlarm = waitingTimer.isAlarm,
    )
  }

  return Pair(completedTimers, waitingTimers)
}
