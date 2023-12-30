package org.sopt.linkminddata.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.linkminddata.repository.DummyRepositoryImpl
import org.sopt.linkminddomain.repository.DummyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
  @Singleton
  @Binds
  abstract fun bindsDummyRepository(dummyRepository: DummyRepositoryImpl): DummyRepository
}
