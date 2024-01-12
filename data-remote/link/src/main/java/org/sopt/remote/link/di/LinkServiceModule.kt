package org.sopt.remote.link.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.network.di.AuthLinkMindRetrofit
import org.sopt.remote.link.api.LinkService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LinkServiceModule {

  @Singleton
  @Provides
  fun provideCategoryService(@AuthLinkMindRetrofit retrofit: Retrofit): LinkService =
    retrofit.create(LinkService::class.java)
}
