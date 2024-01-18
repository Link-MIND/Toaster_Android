package org.sopt.dataremote.category.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.category.CategoryDuplicate

@Serializable
data class ResponseCategoryDuplicateDTO(
  @SerialName("isDupicated")
  val isDuplicated: Boolean,
)

internal fun ResponseCategoryDuplicateDTO.toCoreModel() = CategoryDuplicate(isDuplicate = this.isDuplicated)
