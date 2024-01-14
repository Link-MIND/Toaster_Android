package org.sopt.dataremote.category.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class RequestCategoryTitleDto(
  @SerialName("categoryTitle")
  val categoryTitle:String
)


