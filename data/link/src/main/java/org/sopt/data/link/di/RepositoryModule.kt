package org.sopt.data.link.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.link.repository.LinkRepoImpl
import org.sopt.domain.link.repository.LinkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Singleton
  @Binds
  abstract fun bindLinkRepository(
    linkRepoImpl: LinkRepoImpl,
  ): LinkRepository
}
