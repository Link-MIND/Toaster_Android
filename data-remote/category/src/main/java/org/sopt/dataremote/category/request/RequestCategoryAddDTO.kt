package org.sopt.dataremote.category.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCategoryAddDTO(
  @SerialName("categoryTitle")
  val categoryTitle: String,
)
