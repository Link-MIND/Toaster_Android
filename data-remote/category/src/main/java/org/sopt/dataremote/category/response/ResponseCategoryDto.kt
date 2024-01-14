package org.sopt.dataremote.category.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.category.CategoryList

@Serializable
data class ResponseCategoryDto(
  @SerialName("categoryId")
  val categoryId: Long,
  @SerialName("categoryTitle")
  val categoryTitle: String,
  @SerialName("toastNum")
  val toastNum: Int,
)

internal fun ResponseCategoryDto.toCoreModel() = CategoryList(
  categoryId = categoryId,
  categoryTitle = categoryTitle,
  toastNum = toastNum,
)
