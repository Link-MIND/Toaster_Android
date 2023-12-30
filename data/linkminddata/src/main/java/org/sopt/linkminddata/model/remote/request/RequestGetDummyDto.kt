package org.sopt.linkminddata.model.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestGetDummyDto(
  @SerialName("dummy")
  val request: String,
)
