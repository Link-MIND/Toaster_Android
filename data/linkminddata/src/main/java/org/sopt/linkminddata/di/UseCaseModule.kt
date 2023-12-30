package org.sopt.linkminddata.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.linkminddomain.repository.DummyRepository
import org.sopt.linkminddomain.usecase.DummyUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
  @Provides
  @Singleton
  fun provideDummyUseCase(dummyRepository: DummyRepository): DummyUseCase {
    return DummyUseCase(dummyRepository)
  }
}
