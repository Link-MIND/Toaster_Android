package org.sopt.timer.source.remote

import org.sopt.network.model.response.base.BaseResponse
import org.sopt.timer.model.remote.request.RequestPostTimerDto
import org.sopt.timer.model.remote.response.ResponseGetTimerPageDto

interface TimerRemoteDataSource {
  suspend fun getTimerMain(): BaseResponse<ResponseGetTimerPageDto>

  suspend fun postTimer(requestPostTimerDto: RequestPostTimerDto): BaseResponse<Unit>

  suspend fun deleteTimer(timerId: Int): BaseResponse<Unit>
}
