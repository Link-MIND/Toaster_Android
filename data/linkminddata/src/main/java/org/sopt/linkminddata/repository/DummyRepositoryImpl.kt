package org.sopt.linkminddata.repository

import org.sopt.linkminddata.source.ExampleDataSource
import org.sopt.linkminddomain.entity.DummyEntity
import org.sopt.linkminddomain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
  private val exampleDataSource: ExampleDataSource,
) : DummyRepository {
  override suspend fun getDummy(): Result<DummyEntity> =
    runCatching { exampleDataSource.getDummy().toDomainModel() }
}
