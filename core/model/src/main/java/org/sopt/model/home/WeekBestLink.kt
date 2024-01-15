package org.sopt.model.home

data class WeekBestLink(
  val toastId: Long,
  val toastTitle: String,
  val toastImg: String? = "",
  val toastLink: String,
)
