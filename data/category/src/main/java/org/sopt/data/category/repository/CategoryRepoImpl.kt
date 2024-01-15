package org.sopt.data.category.repository

import org.sopt.data.category.datasource.RemoteCategoryDataSource
import org.sopt.domain.category.category.repository.CategoryRepository
import org.sopt.model.category.CategoryDuplicate
import org.sopt.model.category.CategoryLinkCondition
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class CategoryRepoImpl @Inject constructor(
  private val remoteCategoryDataSource: RemoteCategoryDataSource,
) : CategoryRepository {
  override suspend fun getCategoryAll(): Result<CategoryList> =
    runCatching { remoteCategoryDataSource.getCategoryAll() }

  override suspend fun postAddCategory(categoryTitle: String): Result<Int> =
    runCatching { remoteCategoryDataSource.postAddCategoryTitle(categoryTitle) }

  override suspend fun deleteCategory(deleteCategoryList: List<Long>): Result<Unit> =
    runCatching { remoteCategoryDataSource.deleteCategory(deleteCategoryList) }

    override suspend fun getCategoryDuplicate(title: String): Result<CategoryDuplicate> =
      runCatching { remoteCategoryDataSource.getCategoryDuplicate(title) }

    override suspend fun getCategoryLink(filter: String, isAllCategory: Boolean): Result<CategoryLinkCondition> =
      runCatching { remoteCategoryDataSource.getCategoryLink(filter, isAllCategory) }

    override suspend fun patchCategoryEdit(): Result<Unit> =
      runCatching { remoteCategoryDataSource.patchCategoryEdit() }
  }
