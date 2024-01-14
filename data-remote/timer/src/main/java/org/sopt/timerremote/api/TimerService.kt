package org.sopt.timerremote.api

import org.sopt.network.model.response.base.BaseResponse
import org.sopt.timer.model.remote.request.RequestPatchTimerDto
import org.sopt.timer.model.remote.request.RequestPostTimerDto
import org.sopt.timer.model.remote.response.ResponseGetTimerDto
import org.sopt.timer.model.remote.response.ResponseGetTimerPageDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TimerService {
  @GET("timer/main")
  suspend fun getTimerMain(): BaseResponse<ResponseGetTimerPageDto>

  @POST("timer")
  suspend fun postTimer(
    @Body requestPostTimerDto: RequestPostTimerDto,
  ): BaseResponse<Unit>

  @PATCH("timer/datetime")
  suspend fun patchTimer(
    @Path("timerId") timerId: Int,
    @Body requestPatchTimerDto: RequestPatchTimerDto
  ): BaseResponse<Unit>

  @DELETE("timer/{timerId}")
  suspend fun deleteTimer(
    @Path("timerId") timerId: Int,
  ): BaseResponse<Unit>

  @GET("timer")
  suspend fun getTimer(
    @Path("timerId") timerId: Int,
  ): BaseResponse<ResponseGetTimerDto>
}
