package org.sopt.dataremote.category.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.category.CategoryChangePriority
import org.sopt.model.category.CategoryChangeTitle

@Serializable
data class RequestCategoryEditDTO(
  @SerialName("changeCategoryPriorityList")
  val changeCategoryPriorityList: List<ChangeCategoryPriority>,
  @SerialName("changeCategoryTitleList")
  val changeCategoryTitleList: List<ChangeCategoryTitle>

)
@Serializable
data class ChangeCategoryTitle(
  @SerialName("categoryId")
  val categoryId: Long,
  @SerialName("newTitle")
  val newTitle: String
)
@Serializable
data class ChangeCategoryPriority(
  @SerialName("categoryId")
  val categoryId: Long,
  @SerialName("newPriority")
  val newPriority: Long
)

fun CategoryChangePriority.toRequestDTO() = ChangeCategoryPriority(
  categoryId = this.categoryId,
  newPriority = this.newPriority,
)

fun CategoryChangeTitle.toRequestDTO()= ChangeCategoryTitle(
  categoryId=this.categoryId,
  newTitle = this.newTitle
)

fun Pair<List<ChangeCategoryTitle>, List<ChangeCategoryPriority>>.toRequestDIO()= RequestCategoryEditDTO(
  changeCategoryTitleList = first,
  changeCategoryPriorityList = second
)
