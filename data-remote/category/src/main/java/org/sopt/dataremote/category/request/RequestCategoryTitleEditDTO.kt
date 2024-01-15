package org.sopt.dataremote.category.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCategoryTitleEditDTO(
  @SerialName("changeCategoryPriorityList")
  val changeCategoryPriorityList: List<ChangeCategoryPriority>,
  @SerialName("changeCategoryTitleList")
  val changeCategoryTitleList: List<ChangeCategoryTitle>

)
@Serializable
data class ChangeCategoryTitle(
  @SerialName("categoryId")
  val categoryId: Int,
  @SerialName("newTitle")
  val newTitle: String
)
@Serializable
data class ChangeCategoryPriority(
  @SerialName("categoryId")
  val categoryId: Int,
  @SerialName("newTitle")
  val newPriority: Int
)

