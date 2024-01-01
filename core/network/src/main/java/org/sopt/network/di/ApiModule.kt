package org.sopt.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.network.service.TokenRefreshService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  @Provides
  @Singleton
  fun provideTokenRefreshService(@LinkMindRetrofit retrofit: Retrofit) =
    retrofit.create(TokenRefreshService::class.java)
}
