package org.sopt.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.home.repository.HomeRepoImpl
import org.sopt.home.repository.HomeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryHomeModule {
  @Singleton
  @Binds
  abstract fun bindHomeRepository(
    homeRepoImpl: HomeRepoImpl,
  ): HomeRepository
}
