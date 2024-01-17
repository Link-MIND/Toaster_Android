package org.sopt.dataremote.category.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCategoryDeleteDTO(
  @SerialName("deleteCategoryDto")
  val deleteCategoryList: Long,
)

fun Long.toRequestDTO() = RequestCategoryDeleteDTO(deleteCategoryList = this)
