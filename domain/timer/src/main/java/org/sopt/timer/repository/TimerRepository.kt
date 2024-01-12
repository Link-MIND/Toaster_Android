package org.sopt.timer.repository

import org.sopt.model.timer.Timer
import org.sopt.model.timer.TimerData

interface TimerRepository {
  suspend fun getTimerMain(): Result<Pair<List<Timer>, List<Timer>>?>

  suspend fun postTimer(timerData: TimerData): Result<Unit>
}
