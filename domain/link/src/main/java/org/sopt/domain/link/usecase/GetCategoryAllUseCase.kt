package org.sopt.domain.link.usecase

import org.sopt.domain.link.repository.LinkRepository
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class GetCategoryAllUseCase @Inject constructor(
  private val categoryRepository: LinkRepository,
) {
  suspend operator fun invoke(): Result<List<CategoryList>> = categoryRepository.getCategoryAll()
}
