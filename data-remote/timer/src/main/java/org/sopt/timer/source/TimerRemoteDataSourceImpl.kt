package org.sopt.timer.source

import org.sopt.timer.api.TimerService
import org.sopt.timer.source.remote.TimerRemoteDataSource
import javax.inject.Inject

class TimerRemoteDataSourceImpl @Inject constructor(
  private val timerService: TimerService
) : TimerRemoteDataSource {
  //TODO
}
