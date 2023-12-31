package org.sopt.authimpl.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.auth.model.Auth

@Serializable
data class RequestPostAuthDto(
  @SerialName("socialType")
  val socialType: String,
  @SerialName("fcmToken")
  val fcmToken: String,
) {
  companion object {
    fun Auth.toDataModel() =
      RequestPostAuthDto(
        this.socialType,
        this.deviceToken,
      )
  }
}
