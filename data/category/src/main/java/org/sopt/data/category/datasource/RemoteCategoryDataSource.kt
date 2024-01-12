package org.sopt.data.category.datasource

import org.sopt.model.sample.category.CategoryList

interface RemoteCategoryDataSource {
  suspend fun getCategoryAll(): List<CategoryList>
}
