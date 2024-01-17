package org.sopt.dataremote.category.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dataremote.category.api.SearchService
import org.sopt.network.di.AuthLinkMindRetrofit
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchServiceModule {

  @Singleton
  @Provides
  fun provideSearchService(@AuthLinkMindRetrofit retrofit: Retrofit): SearchService =
    retrofit.create(SearchService::class.java)
}
