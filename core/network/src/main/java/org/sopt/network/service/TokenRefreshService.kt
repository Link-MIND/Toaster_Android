package org.sopt.network.service

import org.sopt.network.model.response.ResponsePostAuthRefreshDto
import org.sopt.network.model.response.base.BaseResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface TokenRefreshService {
  @POST("auth/token")
  suspend fun postAuthRefresh(
    @Header("refreshToken") refreshToken: String,
  ): BaseResponse<ResponsePostAuthRefreshDto>
}
