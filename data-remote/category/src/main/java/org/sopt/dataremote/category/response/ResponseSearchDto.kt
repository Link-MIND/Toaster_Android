package org.sopt.dataremote.category.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.category.Category
import org.sopt.model.category.SearchResultList
import org.sopt.model.category.Toast

@Serializable
data class ResponseSearchDto(
  @SerialName("toasts")
  val toasts: List<ResponseToastDto>?,
  @SerialName("categories")
  val categories: List<ResponseCategoryDto>?,
  @SerialName("keyword")
  val keyword: String?,
) {

  @Serializable
  data class ResponseToastDto(
    @SerialName("toastId")
    val toastId: Long,
    @SerialName("categoryTitle")
    val categoryTitle: String?,
    @SerialName("isRead")
    val isRead: Boolean?,
    @SerialName("linkUrl")
    val linkUrl: String?,
    @SerialName("toastTitle")
    val toastTitle: String?,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String?,
  )

  @Serializable
  data class ResponseCategoryDto(
    @SerialName("categoryId")
    val categoryId: Long,
    @SerialName("title")
    val categoryTitle: String,
    @SerialName("toastNum")
    val toastNum: Int,
  )
}

internal fun ResponseSearchDto.ResponseToastDto.toCoreModel() = Toast(
  toastId = toastId,
  categoryTitle = categoryTitle,
  isRead = isRead,
  linkUrl = linkUrl,
  toastTitle = toastTitle,
  thumbnailUrl = thumbnailUrl,
)

internal fun ResponseSearchDto.ResponseCategoryDto.toCoreModel() = Category(
  categoryId = categoryId,
  categoryTitle = categoryTitle,
  toastNum = toastNum.toLong(),
)

internal fun ResponseSearchDto.toCoreModel() = SearchResultList(
  toasts = toasts?.map { it.toCoreModel() },
  categories = categories?.map { it.toCoreModel() },
  keyword = keyword,
)
