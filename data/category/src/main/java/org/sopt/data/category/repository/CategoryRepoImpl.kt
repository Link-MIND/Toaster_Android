package org.sopt.data.category.repository

import org.sopt.data.category.datasource.RemoteCategoryDataSource
import org.sopt.domain.category.category.repository.CategoryRepository
import org.sopt.model.category.CategoryDuplicate
import org.sopt.model.category.CategoryLinkList
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

  override suspend fun getCategoryLink(filter: String?, categoryId: Long?): Result<CategoryLinkList> =
    runCatching { remoteCategoryDataSource.getCategoryLink(filter, categoryId) }

  override suspend fun patchCategoryEditTitle(
    categoryId: Long,
    newTitle: String?,
  ): Result<Unit> =
    runCatching { remoteCategoryDataSource.patchCategoryEditTitle(categoryId, newTitle) }

  override suspend fun patchCategoryEditPriority(categoryId: Long, newPriority: Int): Result<Unit> =
    runCatching { remoteCategoryDataSource.patchCategoryPriority(categoryId, newPriority) }
}
