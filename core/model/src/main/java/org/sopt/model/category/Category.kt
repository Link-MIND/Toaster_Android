package org.sopt.model.category

data class Category(
  val categoryId: Long? = 0,
  val categoryTitle: String?,
  var toastNum: Long,
)
