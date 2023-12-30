package org.sopt.linkminddomain.usecase

import org.sopt.linkminddomain.repository.DummyRepository

class DummyUseCase(
  private val dummyRepository: DummyRepository,
) {
  suspend operator fun invoke() = dummyRepository.getDummy()
}
