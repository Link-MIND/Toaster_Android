package org.sopt.domain.category.category.repository

import org.sopt.model.category.CategoryDuplicate
import org.sopt.model.category.CategoryLinkList
import org.sopt.model.category.CategoryList

interface CategoryRepository {
  suspend fun getCategoryAll(): Result<CategoryList>

  suspend fun postAddCategory(categoryTitle: String): Result<Int>

  suspend fun deleteCategory(deleteCategoryList: List<Long>): Result<Unit>

  suspend fun getCategoryDuplicate(title: String): Result<CategoryDuplicate>

  suspend fun getCategoryLink(filter: String?, categoryId: Long?): Result<CategoryLinkList>

  suspend fun patchCategoryEditTitle(
    categoryId: Long,
    newTitle: String?,
  ): Result<Unit>

  suspend fun patchCategoryEditPriority(
    categoryId: Long,
    newPriority: Int,
  ): Result<Unit>
}
