package org.sopt.dataremote.category.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.category.Category
import org.sopt.model.category.CategoryList

@Serializable
data class ResponseCategoryEntireDto(
  @SerialName("toastNumberInEntire")
  val toastNumberInEntire: Long,
  @SerialName("categories")
  val categories: List<ResponseCategoryDto>,
) {
  @Serializable
  data class ResponseCategoryDto(
    @SerialName("categoryId")
    val categoryId: Long,
    @SerialName("categoryTitle")
    val categoryTitle: String,
    @SerialName("toastNum")
    val toastNum: Int,
  )
}

internal fun ResponseCategoryEntireDto.ResponseCategoryDto.toCoreModel() = Category(
  categoryId = categoryId,
  categoryTitle = categoryTitle,
  toastNum = toastNum.toLong(),
)

internal fun ResponseCategoryEntireDto.toCoreModel() = CategoryList(
  toastNumberInEntire = toastNumberInEntire,
  categories = categories.map { it.toCoreModel() },
)
