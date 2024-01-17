package org.sopt.data.category.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.category.repository.CategoryRepoImpl
import org.sopt.data.category.repository.SearchRepoImpl
import org.sopt.domain.category.category.repository.CategoryRepository
import org.sopt.domain.category.category.repository.SearchRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Singleton
  @Binds
  abstract fun bindCategoryRepository(
    categoryRepository: CategoryRepoImpl,
  ): CategoryRepository

  @Singleton
  @Binds
  abstract fun bindSearchRepository(
    searchRepository: SearchRepoImpl,
  ): SearchRepository
}
