package api

import org.sopt.network.model.response.base.BaseResponse
import request.RequestIsAllowedDto
import response.ResponseGetUserMyPageDto
import response.ResponseGetUserSettingDto
import response.ResponseIsAllowedDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserService {

  companion object {
    const val USER = "user"
    const val MYPAGE = "my-page"
    const val SETTING = "settings"
    const val NOTIFICATION = "notification"
  }

  @GET("/$USER/$MYPAGE")
  suspend fun getMyPage(): BaseResponse<ResponseGetUserMyPageDto>

  @GET("/$USER/$SETTING")
  suspend fun getSetting(): BaseResponse<ResponseGetUserSettingDto>

  @PATCH("/$USER/$NOTIFICATION")
  suspend fun patchPush(@Body requestIsAllowedDto: RequestIsAllowedDto): BaseResponse<ResponseIsAllowedDto>
}
