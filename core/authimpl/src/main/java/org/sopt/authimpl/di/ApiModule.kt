package org.sopt.authimpl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.authimpl.sourceimpl.remote.api.AuthService
import org.sopt.network.di.AuthLinkMindRetrofit
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  @Provides
  @Singleton
  fun provideAuthService(@AuthLinkMindRetrofit retrofit: Retrofit): AuthService =
    retrofit.create(AuthService::class.java)
}
