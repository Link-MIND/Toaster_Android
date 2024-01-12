package org.sopt.timer.repository

import org.sopt.model.timer.TimerData
import org.sopt.model.timer.Timer

interface TimerRepository {
  suspend fun getTimerMain(): Result<Pair<List<Timer>, List<Timer>>?>

  suspend fun postTimer(timerData: TimerData): Result<Unit>
}
