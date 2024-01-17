package org.sopt.data.category.datasource

import org.sopt.model.category.CategoryDuplicate
import org.sopt.model.category.CategoryLinkList
import org.sopt.model.category.CategoryList

interface RemoteCategoryDataSource {
  suspend fun getCategoryAll(): CategoryList

  suspend fun postAddCategoryTitle(categoryTitle: String?): Int

  suspend fun deleteCategory(deleteCategoryList: Long)

  suspend fun getCategoryDuplicate(title: String): CategoryDuplicate

  suspend fun getCategoryLink(filter: String?, categoryId: Long?): CategoryLinkList

  suspend fun patchCategoryPriority(categoryId: Long, newPriority: Int)

  suspend fun patchCategoryEditTitle(categoryId: Long, newTitle: String)
}
