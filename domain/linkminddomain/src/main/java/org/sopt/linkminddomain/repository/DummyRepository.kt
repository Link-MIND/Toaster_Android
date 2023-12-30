package org.sopt.linkminddomain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.linkminddomain.entity.DummyEntity

interface DummyRepository {
  suspend fun getDummy() : Result<DummyEntity>
}
