package org.sopt.remote.home.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.home.WeekBestLink

@Serializable
data class ResponseWeekBestLinkDto(
  @SerialName("toastId")
  val toastId: Long,
  @SerialName("toastTitle")
  val toastTitle: String,
  @SerialName("toastImg")
  val toastImg: String,
  @SerialName("toastLink")
  val toastLink: String,
)


internal fun ResponseWeekBestLinkDto.toCoreModel() = WeekBestLink(
  toastId = toastId,
  toastTitle = toastTitle,
  toastImg = toastImg,
  toastLink = toastLink,
)
