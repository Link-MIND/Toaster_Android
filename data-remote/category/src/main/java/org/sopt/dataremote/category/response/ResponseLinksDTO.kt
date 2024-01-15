package org.sopt.dataremote.category.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLinksDTO(
  @SerialName("allToastNum")
  val allToastNum: Int,
  @SerialName("toastListDto")
  val toastListDto: List<ToastDto>,
)

@Serializable
data class ToastDto(
  @SerialName("toastId")
  val toastId: Int,
  @SerialName("categoryTitle")
  val categoryTitle: String,
  @SerialName("isRead")
  val isRead: Boolean,
  @SerialName("linkUrl")
  val linkUrl: String,
  @SerialName("toastTitle")
  val toastTitle: String,
  @SerialName("thumbnailUrl")
  val thumbnailUrl: String,
)
