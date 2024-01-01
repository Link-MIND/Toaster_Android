package org.sopt.auth.model

data class Token(
  val accessToken: String,
  val refreshToken: String,
  val deviceToken: String,
)
