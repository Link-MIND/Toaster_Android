package org.sopt.authimpl.sourceimpl.remote.api

import org.sopt.authimpl.model.request.RequestPostAuthDto
import org.sopt.authimpl.model.response.ResponsePostAuthDto
import org.sopt.authimpl.model.response.ResponsePostSignOutDto
import org.sopt.network.model.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
  @POST(AUTH)
  suspend fun postLogin(
    @Header(AUTHORIZATION) socialAccessToken: String,
    @Body requestLoginDto: RequestPostAuthDto,
  ): BaseResponse<ResponsePostAuthDto>

  @POST("$AUTH/$SIGNOUT")
  suspend fun postSignOut(): ResponsePostSignOutDto

  @DELETE("$AUTH/$WITHDRAW")
  suspend fun deleteUser(): BaseResponse<Unit>

  companion object {
    const val AUTH = "auth"
    const val SIGNOUT = "sign-out"
    const val WITHDRAW = "withdraw"
    const val AUTHORIZATION = "Autorization"
  }
}
