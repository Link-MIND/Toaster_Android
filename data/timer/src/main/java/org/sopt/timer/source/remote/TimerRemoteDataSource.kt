package org.sopt.timer.source.remote

import org.sopt.network.model.response.base.BaseResponse
import org.sopt.timer.model.remote.ResponseGetTimerPageDto

interface TimerRemoteDataSource {
  suspend fun getTimerMain(): BaseResponse<ResponseGetTimerPageDto>
}
