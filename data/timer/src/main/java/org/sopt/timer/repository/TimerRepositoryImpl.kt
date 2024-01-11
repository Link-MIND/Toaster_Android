package org.sopt.timer.repository

import org.sopt.timer.source.remote.TimerRemoteDataSource
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
  private val timerRemoteDataSource: TimerRemoteDataSource
) : TimerRepository {
  //TODO
}
