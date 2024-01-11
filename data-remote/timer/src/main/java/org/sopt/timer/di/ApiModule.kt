package org.sopt.timer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.network.di.AuthLinkMindRetrofit
import org.sopt.timer.api.TimerService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  @Provides
  @Singleton
  fun provideTimerService(@AuthLinkMindRetrofit retrofit: Retrofit): TimerService =
    retrofit.create(TimerService::class.java)
}
