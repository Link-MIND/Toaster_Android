package org.sopt.authimpl.source.remote

import org.sopt.authimpl.model.request.RequestPostAuthDto
import org.sopt.authimpl.model.response.ResponsePostAuthDto
import org.sopt.authimpl.model.response.ResponsePostSignOutDto
import org.sopt.network.model.response.base.BaseResponse

interface AuthRemoteDataSource {
  suspend fun authenticate(
    socialToken: String,
    postAuthDto: RequestPostAuthDto,
  ): BaseResponse<ResponsePostAuthDto>

  suspend fun signout(): ResponsePostSignOutDto

  suspend fun withdraw(): BaseResponse<Unit>
}
