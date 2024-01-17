package org.sopt.remote.home.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.home.WeekBestLink

@Serializable
data class ResponseWeekBestLinkDto(
  @SerialName("linkId")
  val linkId: Long,
  @SerialName("linkTitle")
  val linkTitle: String,
  @SerialName("linkImg")
  val linkImg: String?,
  @SerialName("linkUrl")
  val linkUrl: String,
)

internal fun ResponseWeekBestLinkDto.toCoreModel() = WeekBestLink(
  toastId = linkId,
  toastTitle = linkTitle,
  toastImg = linkImg,
  toastLink = linkUrl,
)
