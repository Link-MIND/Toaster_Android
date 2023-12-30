package org.sopt.linkminddata.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.linkminddata.source.ExampleDataSource
import org.sopt.linkminddata.sourceimpl.ExampleDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
  @Singleton
  @Binds
  abstract fun bindsExampleDataSource(exampleDataSource: ExampleDataSourceImpl): ExampleDataSource
}
