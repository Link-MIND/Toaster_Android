package org.sopt.remote.link.api

import org.sopt.network.model.response.base.BaseResponse
import org.sopt.remote.link.request.RequestIsReadDto
import org.sopt.remote.link.request.RequestWriteDto
import org.sopt.remote.link.response.ResponseIsReadDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface LinkService {
  companion object {
    const val TOAST = "toast"
    const val DELETE = "delete"
    const val ISREAD = "is-read"
    const val SAVE = "save"
  }

  @POST("/$TOAST/$SAVE")
  suspend fun postLink(
    @Body requestWriteDto: RequestWriteDto,
  ): BaseResponse<Unit>

  @DELETE("/$TOAST/$DELETE")
  suspend fun deleteLink(@Query("toastId") toastId: Long): BaseResponse<Unit>

  @PATCH("/$TOAST/$ISREAD")
  suspend fun patchLink(@Body requestIsReadDto: RequestIsReadDto): BaseResponse<ResponseIsReadDto>
}
