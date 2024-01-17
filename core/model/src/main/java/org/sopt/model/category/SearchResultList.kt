package org.sopt.model.category

data class SearchResultList(
  val toasts: List<Toast>?,
  val categories: List<Category>?,
  val keyword: String?,
)
