package org.sopt.data.category.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.category.repository.CategoryRepoImpl
import org.sopt.domain.category.category.repository.CategoryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Singleton
  @Binds
  abstract fun bindCategoryRepository(
    categoryRepoImpl: CategoryRepoImpl,
  ): CategoryRepository

}
