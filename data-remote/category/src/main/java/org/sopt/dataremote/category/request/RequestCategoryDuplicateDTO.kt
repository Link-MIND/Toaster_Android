package org.sopt.dataremote.category.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCategoryDuplicateDTO(
  @SerialName("title")
  val title: String,
)
