package org.sopt.auth.model

data class Auth(
  val socialToken: String,
  val deviceToken: String,
  val socialType: String,
)
