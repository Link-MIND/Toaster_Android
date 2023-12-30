package org.sopt.linkminddataremote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.linkminddataremote.source.api.DummyService
import org.sopt.network.di.LinkMindRetrofit
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
  @Provides
  @Singleton
  fun provideDummyService(@LinkMindRetrofit retrofit: Retrofit): DummyService =
    retrofit.create(DummyService::class.java)
}
