package org.sopt.linkminddata.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.linkminddata.sourceimpl.api.ExampleService
import org.sopt.network.di.LinkMindRetrofit
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
  @Provides
  @Singleton
  fun provideExampleService(@LinkMindRetrofit retrofit: Retrofit): ExampleService =
    retrofit.create(ExampleService::class.java)
}
