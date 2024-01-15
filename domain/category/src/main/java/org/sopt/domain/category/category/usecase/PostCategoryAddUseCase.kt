package org.sopt.domain.category.category.usecase

import org.sopt.domain.category.category.repository.CategoryRepository
import javax.inject.Inject

class PostCategoryAddUseCase @Inject constructor(
  private val categoryRepository: CategoryRepository,
) {
  suspend operator fun invoke() = categoryRepository.postCategoryAdd()
}
