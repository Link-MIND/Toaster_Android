package org.sopt.linkminddomain.repository

import org.sopt.linkminddomain.entity.DummyEntity

interface DummyRepository {
  suspend fun getDummy(): Result<DummyEntity>
}
