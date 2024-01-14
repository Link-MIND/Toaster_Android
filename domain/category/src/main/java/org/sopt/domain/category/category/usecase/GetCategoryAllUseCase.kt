package org.sopt.domain.category.category.usecase

import org.sopt.domain.category.category.repository.CategoryRepository
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class GetCategoryAllUseCase @Inject constructor(
  private val categoryRepository: CategoryRepository,
) {
  suspend operator fun invoke(): Result<CategoryList> = categoryRepository.getCategoryAll()
}
