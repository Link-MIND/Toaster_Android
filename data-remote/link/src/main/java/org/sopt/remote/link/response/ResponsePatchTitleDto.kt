package org.sopt.remote.link.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePatchTitleDto(
  @SerialName("updatedTitle")
  val updatedTitle: String,
)
