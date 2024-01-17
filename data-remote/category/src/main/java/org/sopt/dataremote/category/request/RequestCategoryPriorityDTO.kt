package org.sopt.dataremote.category.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.category.CategoryChangePriority

@Serializable
data class RequestCategoryPriorityDTO(
  @SerialName("categoryId")
  val categoryId: Long,
  @SerialName("newPriority")
  val newPriority: Int,

)
fun RequestCategoryPriorityDTO.toRequestDTO() = CategoryChangePriority(
  categoryId = this.categoryId,
  newPriority = this.newPriority,
)
