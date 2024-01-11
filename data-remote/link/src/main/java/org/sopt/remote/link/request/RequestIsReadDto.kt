package org.sopt.remote.link.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestIsReadDto(
  @SerialName("toastId")
  val toastId: Long,
  @SerialName("isRead")
  val isRead: Boolean,
)
