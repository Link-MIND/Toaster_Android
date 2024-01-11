package org.sopt.remote.link.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.link.datasource.RemoteLinkDataSource
import org.sopt.remote.link.datasource.RemoteLinkDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LinkDataSourceModule {
  @Singleton
  @Binds
  abstract fun bindRemoteLectureProviderDatasource(
    remoteLinkDataSourceImpl: RemoteLinkDataSourceImpl,
  ): RemoteLinkDataSource
}
