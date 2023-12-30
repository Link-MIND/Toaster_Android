package org.sopt.linkminddata_remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.linkminddata.source.remote.DummyRemoteDataSource
import org.sopt.linkminddata_remote.source.ExampleDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

  @Singleton
  @Binds
  abstract fun bindsDummyRemoteDataSource(dummyRemoteDataSource: ExampleDataSourceImpl): DummyRemoteDataSource

}
