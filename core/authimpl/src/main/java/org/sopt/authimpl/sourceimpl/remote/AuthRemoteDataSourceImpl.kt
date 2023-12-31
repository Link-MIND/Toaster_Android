package org.sopt.authimpl.sourceimpl.remote

import org.sopt.authimpl.model.request.RequestPostAuthDto
import org.sopt.authimpl.model.response.ResponsePostSignOutDto
import org.sopt.authimpl.source.remote.AuthRemoteDataSource
import org.sopt.authimpl.sourceimpl.remote.api.AuthService
import org.sopt.network.model.response.base.BaseResponse
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
  private val authService: AuthService
): AuthRemoteDataSource {
  override suspend fun authenticate(
    socialToken: String,
    postAuthDto: RequestPostAuthDto
  ) = authService.postLogin(socialToken, postAuthDto)

  override suspend fun signout(): ResponsePostSignOutDto =
    authService.postSignOut()

  override suspend fun withdraw(): BaseResponse<Unit> =
    authService.deleteUser()
}
