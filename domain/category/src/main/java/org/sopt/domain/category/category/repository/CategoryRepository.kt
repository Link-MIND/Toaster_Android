package org.sopt.domain.category.category.repository

import org.sopt.model.category.CategoryChangePriority
import org.sopt.model.category.CategoryChangeTitle
import org.sopt.model.category.CategoryDuplicate
import org.sopt.model.category.CategoryLinkList
import org.sopt.model.category.CategoryList

interface CategoryRepository {
  suspend fun getCategoryAll(): Result<CategoryList>

  suspend fun postAddCategory(categoryTitle: String): Result<Int>

  suspend fun deleteCategory(deleteCategoryList: List<Long>) : Result<Unit>

  suspend fun getCategoryDuplicate(title: String): Result<CategoryDuplicate>

  suspend fun getCategoryLink(filter: String, isAllCategory: Boolean): Result<CategoryLinkList>

  suspend fun patchCategoryEdit(changeCategoryTitle:List<CategoryChangeTitle>, changeCategoryChangePriority: List<CategoryChangePriority>): Result<Unit>
}
