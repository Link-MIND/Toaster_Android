package org.sopt.timer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.timer.source.TimerRemoteDataSourceImpl
import org.sopt.timer.source.remote.TimerRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
  @Binds
  @Singleton
  abstract fun bindsTimerRemoteDataSource(timerRemoteDataSource: TimerRemoteDataSourceImpl): TimerRemoteDataSource
}
