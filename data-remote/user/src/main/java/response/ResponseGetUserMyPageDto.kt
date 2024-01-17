package response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.user.MyPageData

@Serializable
data class ResponseGetUserMyPageDto(
  @SerialName("nickname")
  val nickname: String,
  @SerialName("profile")
  val profile: String,
  @SerialName("allReadToast")
  val allReadToast: Long,
  @SerialName("thisWeekendRead")
  val thisWeekendRead: Long,
  @SerialName("thisWeekendSaved")
  val thisWeekendSaved: Long,
)

fun ResponseGetUserMyPageDto.toCoreModel(): MyPageData {
  return MyPageData(
    nickname = nickname,
    profile = profile,
    allReadToast = allReadToast,
    thisWeekendRead = thisWeekendRead,
    thisWeekendSaved = thisWeekendSaved,
  )
}
