package org.sopt.domain.category.category.usecase

import org.sopt.domain.category.category.repository.CategoryRepository
import javax.inject.Inject

class PatchCategoryEditPriorityUseCase @Inject constructor(
  private val categoryRepository: CategoryRepository,
) {
  suspend operator fun invoke(param: Param): Result<Unit> = categoryRepository.patchCategoryEditPriority(
    categoryId = param.categoryId,
    newPriority = param.newPriority,
  )

  data class Param(
    val categoryId: Long,
    val newPriority: Int,
  )
}
