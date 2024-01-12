package org.sopt.timerremote.api

import org.sopt.network.model.response.base.BaseResponse
import org.sopt.timer.model.remote.request.RequestPostTimerDto
import org.sopt.timer.model.remote.response.ResponseGetTimerPageDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TimerService {
  @GET("timer/main")
  suspend fun getTimerMain(): BaseResponse<ResponseGetTimerPageDto>

  @POST("timer")
  suspend fun postTimer(
    @Body requestPostTimerDto: RequestPostTimerDto
  ): BaseResponse<Unit>
}
