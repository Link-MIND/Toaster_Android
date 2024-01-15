package org.sopt.timerremote.api

import org.sopt.network.model.response.base.BaseResponse
import org.sopt.timerremote.model.request.RequestPatchTimerDto
import org.sopt.timerremote.model.request.RequestPostTimerDto
import org.sopt.timerremote.model.response.ResponseGetTimerDto
import org.sopt.timerremote.model.response.ResponseGetTimerPageDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TimerService {
  @GET("$TIMER/$MAIN")
  suspend fun getTimerMain(): BaseResponse<ResponseGetTimerPageDto>

  @POST(TIMER)
  suspend fun postTimer(
    @Body requestPostTimerDto: RequestPostTimerDto,
  ): BaseResponse<Unit>

  @PATCH("$TIMER/$DATE_TIME/{$TIMER_ID}")
  suspend fun patchTimer(
    @Path(TIMER_ID) timerId: Int,
    @Body requestPatchTimerDto: RequestPatchTimerDto,
  ): BaseResponse<Unit>

  @DELETE("$TIMER/{$TIMER_ID}")
  suspend fun deleteTimer(
    @Path(TIMER_ID) timerId: Int,
  ): BaseResponse<Unit>

  @GET(TIMER)
  suspend fun getTimer(
    @Path(TIMER_ID) timerId: Int,
  ): BaseResponse<ResponseGetTimerDto>

  @PATCH("$TIMER/$ALARM/{$TIMER_ID}")
  suspend fun patchAlarm(
    @Path(TIMER_ID) timerId: Int,
  ): BaseResponse<Unit>

  companion object{
    const val TIMER = "timer"
    const val MAIN = "main"
    const val DATE_TIME = "datetime"
    const val ALARM = "alarm"
    const val TIMER_ID = "timerId"
  }
}
