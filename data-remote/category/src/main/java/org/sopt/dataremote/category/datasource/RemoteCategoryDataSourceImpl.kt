package org.sopt.dataremote.category.datasource

import org.sopt.data.category.datasource.RemoteCategoryDataSource
import org.sopt.dataremote.category.api.CategoryService
import org.sopt.dataremote.category.request.RequestCategoryDeleteDTO
import org.sopt.dataremote.category.request.RequestCategoryTitleDto
import org.sopt.dataremote.category.request.toRequestDTO
import org.sopt.dataremote.category.response.toCoreModel
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class RemoteCategoryDataSourceImpl @Inject constructor(
  private val categoryService: CategoryService,
) : RemoteCategoryDataSource {
  override suspend fun getCategoryAll(): CategoryList =
    categoryService.getCategoryAll().data!!.toCoreModel()

  override suspend fun postAddCategoryTitle(categoryTitle: String): Int =
    categoryService.postAddCategoryTitle(
      RequestCategoryTitleDto(
        categoryTitle = categoryTitle,
      ),
    ).code

  override suspend fun deleteCategory(deleteCategoryList: List<Long>) {
    categoryService.deleteCategory(
      deleteCategoryList.toRequestDTO(),
    )
  }

  override suspend fun getCategoryDuplicate(title: String) {
    categoryService.getCategoryDuplicate(title = title)
  }

  override suspend fun getCategoryLink(filter: String, isAllCategory: Boolean) {
    categoryService.getCategoryLink(
      filter = filter,
      isAllCategory = isAllCategory,
    )
  }

  override suspend fun patchCategoryEdit() {

  }
}
