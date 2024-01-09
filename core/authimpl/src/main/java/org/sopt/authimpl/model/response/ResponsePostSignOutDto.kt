package org.sopt.authimpl.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostSignOutDto(
  @SerialName("code")
  val code: Int,
  @SerialName("message")
  val message: String,
  @SerialName("data")
  val data: Unit?,
)
