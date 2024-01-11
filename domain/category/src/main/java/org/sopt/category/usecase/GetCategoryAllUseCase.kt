package org.sopt.category.usecase

import org.sopt.category.repository.CategoryRepository

class GetCategoryAllUseCase(
  private val categoryRepository: CategoryRepository,
) {
  suspend operator fun invoke() = categoryRepository.getCategoryAll()
}
