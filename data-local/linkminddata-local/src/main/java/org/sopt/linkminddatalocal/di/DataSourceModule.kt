package org.sopt.linkminddatalocal.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.linkminddata.source.local.DummyLocalDataSource
import org.sopt.linkminddatalocal.source.DummyLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
  @Singleton
  @Binds
  abstract fun bindsDummyLocalDataSource(dummyLocalDataSource: DummyLocalDataSourceImpl): DummyLocalDataSource
}
