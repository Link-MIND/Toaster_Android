package org.sopt.dataremote.category.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.category.datasource.RemoteSearchDataSource
import org.sopt.dataremote.category.datasource.RemoteSearchDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchDataSourceModule {
  @Singleton
  @Binds
  abstract fun bindRemoteSearchDatasource(
    remoteSearchDataSourceImpl: RemoteSearchDataSourceImpl,
  ): RemoteSearchDataSource
}
