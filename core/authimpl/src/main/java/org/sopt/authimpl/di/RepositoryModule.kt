package org.sopt.authimpl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.auth.repository.AuthRepository
import org.sopt.authimpl.repository.AuthRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  @Singleton
  abstract fun bindsAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}
