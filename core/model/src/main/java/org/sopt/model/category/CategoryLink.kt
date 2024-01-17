package org.sopt.model.category

data class CategoryLink(
  val toastId: Long,
  val categoryTitle: String?,
  val isRead: Boolean,
  val linkUrl: String?,
  val toastTitle: String,
  val thumbnailUrl: String?,
)
