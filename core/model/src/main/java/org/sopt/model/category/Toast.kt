package org.sopt.model.category

data class Toast(
  val toastId: Long,
  val categoryTitle: String?,
  val isRead: Boolean?,
  val linkUrl: String?,
  val toastTitle: String?,
  val thumbnailUrl: String?,
)
