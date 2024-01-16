package org.sopt.timer.repository

import org.sopt.model.timer.Timer
import org.sopt.model.timer.TimerData
import org.sopt.timer.source.remote.TimerRemoteDataSource
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
  private val timerRemoteDataSource: TimerRemoteDataSource,
) : TimerRepository {
  override suspend fun getTimerMain(): Result<Pair<List<Timer>, List<Timer>>?> =
    runCatching { timerRemoteDataSource.getTimerMain() }

  override suspend fun postTimer(timerData: TimerData): Result<Unit> =
    runCatching { timerRemoteDataSource.postTimer(timerData) }

  override suspend fun deleteTimer(timerId: Int): Result<Unit> =
    runCatching { timerRemoteDataSource.deleteTimer(timerId) }

  override suspend fun patchTimer(timerId: Int, timerData: TimerData): Result<Unit> =
    runCatching { timerRemoteDataSource.patchTimer(timerId, timerData) }

  override suspend fun patchAlarm(timerId: Int): Result<Unit> =
    runCatching { timerRemoteDataSource.patchAlarm(timerId) }
}
