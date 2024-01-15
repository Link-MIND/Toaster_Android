package org.sopt.domain.category.category.repository

import org.sopt.model.category.CategoryDuplicate
import org.sopt.model.category.CategoryLinkGet
import org.sopt.model.category.CategoryList

interface CategoryRepository {
  suspend fun getCategoryAll(): Result<CategoryList>

  suspend fun postAddCategory(categoryTitle: String): Result<Int>

  suspend fun deleteCategory(deleteCategoryList: List<Long>) : Result<Unit>

  suspend fun getCategoryDuplicate(title: String): Result<CategoryDuplicate>

  suspend fun getCategoryLink(filter: String, isAllCategory: Boolean): Result<CategoryLinkGet>

  suspend fun patchCategoryEdit(): Result<Unit>
}
