package org.sopt.dataremote.category.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCategoryDeleteDTO(
  @SerialName("deleteCategoryList")
  val deleteCategoryList: List<Long>,
)

fun List<Long>.toRequestDTO() = RequestCategoryDeleteDTO(deleteCategoryList = this)
