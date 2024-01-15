package org.sopt.domain.category.category.usecase

import org.sopt.domain.category.category.repository.CategoryRepository
import org.sopt.model.category.CategoryDuplicate
import javax.inject.Inject

class GetCategoryDuplicateUseCase @Inject constructor(
  private val categoryRepository: CategoryRepository,
) {
  suspend operator fun invoke(param: Param): Result<CategoryDuplicate> = categoryRepository.getCategoryDuplicate(
    title = param.title
  )
  data class Param(
    val title: String
  )
}
