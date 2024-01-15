package org.sopt.remote.link.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestWriteDto(
  @SerialName("linkUrl")
  val linkUrl: String,
  @SerialName("categoryId")
  val categoryId: Long?,
)
