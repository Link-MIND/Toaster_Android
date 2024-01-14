package org.sopt.domain.category.category.usecase

import org.sopt.domain.category.category.repository.CategoryRepository
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class PostAddCategoryAllUseCase @Inject constructor(
  private val categoryRepository: CategoryRepository,
) {
  suspend operator fun invoke(param:Param): Result<Int> = categoryRepository.postAddCategory(
    categoryTitle = param.categoryTitle
  )
  data class Param(
    val categoryTitle:String,
  )

}
