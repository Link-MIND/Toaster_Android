package org.sopt.remote.link.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPatchTitleDto(
  @SerialName("toastId")
  val toastId: Long,
  @SerialName("title")
  val title: String,
)
