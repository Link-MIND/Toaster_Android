package org.sopt.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostAuthRefreshDto(
  @SerialName("accessToken")
  val accessToken: String,
  @SerialName("refreshToken")
  val refreshToken: String,
)
