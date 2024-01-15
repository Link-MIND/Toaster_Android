package org.sopt.dataremote.category.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.category.datasource.RemoteCategoryDataSource
import org.sopt.dataremote.category.datasource.RemoteCategoryDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CategoryDataSourceModule {
  @Singleton
  @Binds
  abstract fun bindRemoteCategoryDatasource(
    remoteCategoryDataSourceImpl: RemoteCategoryDataSourceImpl,
  ): RemoteCategoryDataSource
}
