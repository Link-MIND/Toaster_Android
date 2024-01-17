package response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.user.SettingPageData

@Serializable
data class ResponseGetUserSettingDto(
  @SerialName("nickname")
  val nickname: String,
  @SerialName("fcmIsAllowed")
  val fcmIsAllowed: Boolean,
)

fun ResponseGetUserSettingDto.toCoreModel(): SettingPageData {
  return SettingPageData(
    nickname = nickname,
    fcmIsAllowed = fcmIsAllowed,
  )
}
