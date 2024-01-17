package org.sopt.model.user

data class MyPageData(
  val nickname: String,
  val profile: String,
  val allReadToast: Long,
  val thisWeekendRead: Long,
  val thisWeekendSaved: Long,
)
