package org.sopt.dataremote.category.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCategoryEditTitleDTO(
  @SerialName("categoryId")
  val categoryId: Long,
  @SerialName("newTitle")
  val categoryTitle: String,
)
