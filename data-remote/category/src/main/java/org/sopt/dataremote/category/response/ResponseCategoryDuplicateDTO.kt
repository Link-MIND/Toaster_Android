package org.sopt.dataremote.category.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCategoryDuplicateDTO (
  @SerialName("isDuplicated")
  val isDuplicated: Boolean,
)
