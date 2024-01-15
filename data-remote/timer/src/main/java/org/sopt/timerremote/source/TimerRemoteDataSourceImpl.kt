package org.sopt.timerremote.source

import org.sopt.model.timer.Timer
import org.sopt.model.timer.TimerData
import org.sopt.network.model.response.base.BaseResponse
import org.sopt.timer.source.remote.TimerRemoteDataSource
import org.sopt.timerremote.api.TimerService
import org.sopt.timerremote.model.request.toPatchDto
import org.sopt.timerremote.model.request.toPostDto
import org.sopt.timerremote.model.response.toCoreModel
import javax.inject.Inject

class TimerRemoteDataSourceImpl @Inject constructor(
  private val timerService: TimerService,
) : TimerRemoteDataSource {
  override suspend fun getTimerMain(): Pair<List<Timer>, List<Timer>>? = timerService.getTimerMain().data?.toCoreModel()
  override suspend fun postTimer(timerData: TimerData): BaseResponse<Unit> = timerService.postTimer(timerData.toPostDto())

  override suspend fun deleteTimer(timerId: Int): BaseResponse<Unit> = timerService.deleteTimer(timerId)

  override suspend fun patchTimer(timerId: Int, timerData: TimerData): BaseResponse<Unit> =
    timerService.patchTimer(timerId, timerData.toPatchDto())

  override suspend fun patchAlarm(timerId: Int): BaseResponse<Unit> = timerService.patchAlarm(timerId)
}
