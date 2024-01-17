package response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseIsAllowedDto(
  @SerialName("isAllowed")
  val isFcmAllowed: Boolean,
)
