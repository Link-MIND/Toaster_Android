package org.sopt.domain.category.category.usecase

import org.sopt.domain.category.category.repository.CategoryRepository
import javax.inject.Inject

class PatchCategoryEditTitleUseCase @Inject constructor(
  private val categoryRepository: CategoryRepository,
) {
  suspend operator fun invoke(param: Param): Result<Unit> = categoryRepository.patchCategoryEditTitle(
    categoryId = param.categoryId,
    newTitle = param.newTitle,
  )

  data class Param(
    val categoryId: Long,
    val newTitle: String,
  )
}
