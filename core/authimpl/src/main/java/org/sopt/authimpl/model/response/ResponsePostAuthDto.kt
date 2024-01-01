package org.sopt.authimpl.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.auth.model.Token
import org.sopt.auth.model.UserData

@Serializable
data class ResponsePostAuthDto(
  @SerialName("userId")
  val userId: Int,
  @SerialName("accessToken")
  val accessToken: String,
  @SerialName("refreshToken")
  val refreshToken: String,
  @SerialName("fcmToken")
  val fcmToken: String,
  @SerialName("isRegistered")
  val isRegistered: Boolean,
  @SerialName("FcmIsAllowed")
  val fcmIsAllowed: Boolean,
) {
  fun toDomainModel() = Pair(
    Token(
      this.accessToken,
      this.refreshToken,
      this.fcmToken,
    ),
    UserData(
      this.userId,
      this.isRegistered,
      this.fcmIsAllowed,
    ),
  )
}
