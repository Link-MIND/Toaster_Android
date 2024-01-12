package org.sopt.timerremote.source

import org.sopt.network.model.response.base.BaseResponse
import org.sopt.timer.model.remote.request.RequestPostTimerDto
import org.sopt.timer.model.remote.response.ResponseGetTimerPageDto
import org.sopt.timer.source.remote.TimerRemoteDataSource
import org.sopt.timerremote.api.TimerService
import javax.inject.Inject

class TimerRemoteDataSourceImpl @Inject constructor(
  private val timerService: TimerService,
) : TimerRemoteDataSource {
  override suspend fun getTimerMain(): BaseResponse<ResponseGetTimerPageDto> = timerService.getTimerMain()
  override suspend fun postTimer(requestPostTimerDto: RequestPostTimerDto): BaseResponse<Unit> = timerService.postTimer(requestPostTimerDto)
}
