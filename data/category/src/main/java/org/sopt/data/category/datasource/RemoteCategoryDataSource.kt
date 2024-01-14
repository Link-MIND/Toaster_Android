package org.sopt.data.category.datasource

import org.sopt.model.category.CategoryList

interface RemoteCategoryDataSource {
  suspend fun getCategoryAll(): List<CategoryList>

  suspend fun postAddCategoryTitle(categoryTitle:String) : Int
}
