package org.sopt.data.category.repository

import org.sopt.data.category.datasource.RemoteCategoryDataSource
import org.sopt.domain.category.category.repository.CategoryRepository
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class CategoryRepoImpl @Inject constructor(
  private val remoteCategoryDataSource: RemoteCategoryDataSource,
) : CategoryRepository {
  override suspend fun getCategoryAll(): Result<CategoryList> =
    runCatching { remoteCategoryDataSource.getCategoryAll() }

  override suspend fun postAddCategory(categoryTitle: String): Result<Int> =
    runCatching { remoteCategoryDataSource.postAddCategoryTitle(categoryTitle) }
}
