package org.sopt.timer.repository

import org.sopt.model.timer.Timer
import org.sopt.model.timer.TimerData
import org.sopt.timer.model.remote.request.toDataModel
import org.sopt.timer.model.remote.response.toCoreModel
import org.sopt.timer.source.remote.TimerRemoteDataSource
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
  private val timerRemoteDataSource: TimerRemoteDataSource,
) : TimerRepository {
  override suspend fun getTimerMain(): Result<Pair<List<Timer>, List<Timer>>?> =
    runCatching { timerRemoteDataSource.getTimerMain().data?.toCoreModel() }

  override suspend fun postTimer(timerData: TimerData): Result<Unit> =
    runCatching { timerRemoteDataSource.postTimer(timerData.toDataModel()) }
}
