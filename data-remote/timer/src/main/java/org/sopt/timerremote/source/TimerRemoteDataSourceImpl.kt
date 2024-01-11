package org.sopt.timerremote.source

import org.sopt.network.model.response.base.BaseResponse
import org.sopt.timer.model.remote.ResponseGetTimerPageDto
import org.sopt.timerremote.api.TimerService
import org.sopt.timer.source.remote.TimerRemoteDataSource
import javax.inject.Inject

class TimerRemoteDataSourceImpl @Inject constructor(
  private val timerService: TimerService
) : TimerRemoteDataSource {
  override suspend fun getTimerMain(): BaseResponse<ResponseGetTimerPageDto> = timerService.getTimerMain()
}
