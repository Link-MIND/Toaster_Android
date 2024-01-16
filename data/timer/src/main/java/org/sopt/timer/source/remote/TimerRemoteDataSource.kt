package org.sopt.timer.source.remote

import org.sopt.model.timer.Timer
import org.sopt.model.timer.TimerData
import org.sopt.network.model.response.base.BaseResponse

interface TimerRemoteDataSource {
  suspend fun getTimerMain(): Pair<List<Timer>, List<Timer>>?

  suspend fun postTimer(timerData: TimerData): BaseResponse<Unit>
  suspend fun deleteTimer(timerId: Int): BaseResponse<Unit>

  suspend fun patchTimer(timerId: Int, timerData: TimerData): BaseResponse<Unit>
  suspend fun patchAlarm(timerId: Int): BaseResponse<Unit>
}
