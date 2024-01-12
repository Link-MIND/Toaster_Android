package org.sopt.timer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.timer.repository.TimerRepository
import org.sopt.timer.repository.TimerRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  @Singleton
  abstract fun bindsTimerRepository(timerRepository: TimerRepositoryImpl): TimerRepository
}
