package org.sopt.authimpl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.authimpl.source.local.AuthLocalDataSource
import org.sopt.authimpl.source.remote.AuthRemoteDataSource
import org.sopt.authimpl.sourceimpl.local.AuthLocalDataSourceImpl
import org.sopt.authimpl.sourceimpl.remote.AuthRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
  @Binds
  @Singleton
  abstract fun bindsAuthLocalDataSource(authLocalDataSource: AuthLocalDataSourceImpl): AuthLocalDataSource

  @Binds
  @Singleton
  abstract fun bindsAuthRemoteDataSource(authRemoteDataSource: AuthRemoteDataSourceImpl): AuthRemoteDataSource
}
