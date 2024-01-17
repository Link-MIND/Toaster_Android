package request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestIsAllowedDto(
  @SerialName("allowedPush") val allowedPush: Boolean,
)
