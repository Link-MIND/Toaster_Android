package org.sopt.auth.model

data class UserData(
  val userId: Int,
  val isRegistered: Boolean,
  val fcmIsAllowed: Boolean
)
