package org.sopt.dataremote.category.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.category.CategoryChangeTitle

@Serializable
data class RequestCategoryEditTitleDTO(
  @SerialName("categoryId")
  val categoryId: Long,
  @SerialName("categoryTitle")
  val categoryTitle: String?,
)

fun RequestCategoryEditTitleDTO.toRequestDTO() = CategoryChangeTitle(
  categoryId = this.categoryId,
  newTitle = this.categoryTitle,
)