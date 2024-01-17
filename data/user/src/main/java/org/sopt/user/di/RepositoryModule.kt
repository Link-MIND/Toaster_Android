package org.sopt.user.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.user.repository.UserRepository
import org.sopt.user.repository.UserRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Singleton
  @Binds
  abstract fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository
}
