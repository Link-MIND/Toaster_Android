package org.sopt.savelink.ui.model

import org.sopt.model.category.Category

data class Clip(
  val categoryId: Long?,
  val categoryTitle: String,
  val toastNum: Long,
  var isSelected: Boolean,
)

internal fun Category.toModel() = Clip(
  categoryId = categoryId,
  categoryTitle = categoryTitle ?: "",
  toastNum = toastNum,
  isSelected = false,
)
