package org.sopt.timer.repository

import org.sopt.model.timer.Timer
import org.sopt.timer.model.remote.toCoreModel
import org.sopt.timer.source.remote.TimerRemoteDataSource
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
  private val timerRemoteDataSource: TimerRemoteDataSource
) : TimerRepository {
  override suspend fun getTimerMain(): Result<Pair<List<Timer>, List<Timer>>?> =
    runCatching { timerRemoteDataSource.getTimerMain().data?.toCoreModel() }

}
