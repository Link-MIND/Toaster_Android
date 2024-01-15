package org.sopt.domain.category.category.usecase

import org.sopt.domain.category.category.repository.CategoryRepository
import org.sopt.model.category.CategoryLinkGet
import javax.inject.Inject

class GetCategoryLinkUseCase @Inject constructor(
  private val categoryRepository: CategoryRepository,
) {
  suspend operator fun invoke(param: Param): Result<CategoryLinkGet> = categoryRepository.getCategoryLink(
    filter = param.filter,
    isAllCategory = param.isAllCategory
  )

  data class Param(
    val filter: String,
    val isAllCategory: Boolean,
  )
}
