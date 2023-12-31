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
  @POST("auth")
  suspend fun postLogin(
    @Header("Authorization") socialAccessToken: String,
    @Body requestLoginDto: RequestPostAuthDto,
  ): BaseResponse<ResponsePostAuthDto>

  @POST("auth/sign-out")
  suspend fun postSignOut(): ResponsePostSignOutDto

  @DELETE("auth/withdraw")
  suspend fun deleteUser(): BaseResponse<Unit>
}
