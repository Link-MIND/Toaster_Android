package org.sopt.domain.category.category.repository

import org.sopt.model.sample.category.CategoryList

interface CategoryRepository {
  suspend fun getCategoryAll(): Result<List<CategoryList>>
}
