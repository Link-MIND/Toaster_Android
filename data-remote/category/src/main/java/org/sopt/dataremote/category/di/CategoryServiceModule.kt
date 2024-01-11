package org.sopt.dataremote.category.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dataremote.category.api.CategoryService
import org.sopt.network.di.AuthLinkMindRetrofit
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryServiceModule {

  @Singleton
  @Provides
  fun provideCategoryService(@AuthLinkMindRetrofit retrofit: Retrofit): CategoryService =
    retrofit.create(CategoryService::class.java)
}
