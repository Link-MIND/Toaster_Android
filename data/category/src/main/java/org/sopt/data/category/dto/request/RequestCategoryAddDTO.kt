package org.sopt.data.category.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCategoryAddDTO(
  @SerialName("categoryTitle")
  val categoryTitle: String,
)
