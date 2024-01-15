package org.sopt.data.category.datasource

import org.sopt.model.category.CategoryList

interface RemoteCategoryDataSource {
  suspend fun getCategoryAll(): CategoryList

  suspend fun postAddCategoryTitle(categoryTitle: String): Int

  suspend fun deleteCategory(deleteCategoryList: List<Long>)

  suspend fun getCategoryDuplicate(title: String)

  suspend fun getCategoryLink(filter: String, isAllCategory: Boolean)

  suspend fun patchCategoryEdit()
}
