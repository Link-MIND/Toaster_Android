package org.sopt.remote.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.network.di.AuthLinkMindRetrofit
import org.sopt.remote.home.api.HomeService
import retrofit2.Retrofit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object HomeServiceModule {

  @Singleton
  @Provides
  fun provideHomeService(@AuthLinkMindRetrofit retrofit: Retrofit): HomeService =
    retrofit.create(HomeService::class.java)
}
