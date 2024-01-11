package org.sopt.timerremote.api

import org.sopt.network.model.response.base.BaseResponse
import org.sopt.timer.model.remote.ResponseGetTimerPageDto
import retrofit2.http.GET

interface TimerService {
  @GET("timer/main")
  suspend fun getTimerMain(): BaseResponse<ResponseGetTimerPageDto>
}
