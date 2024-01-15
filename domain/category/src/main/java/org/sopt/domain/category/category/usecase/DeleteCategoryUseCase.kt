package org.sopt.domain.category.category.usecase

import org.sopt.domain.category.category.repository.CategoryRepository
import javax.inject.Inject

class DeleteCategoryUseCase @Inject constructor(
  private val categoryRepository: CategoryRepository,
){
  suspend operator fun invoke(param: Param): Result<Unit> = categoryRepository.deleteCategory(
    deleteCategoryList = param.deleteCategoryList
  )
  data class Param(
    val deleteCategoryList: List<Long>
  )
}
