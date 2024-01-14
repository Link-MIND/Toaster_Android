package org.sopt.dataremote.category.datasource

import org.sopt.data.category.datasource.RemoteCategoryDataSource
import org.sopt.dataremote.category.api.CategoryService
import org.sopt.dataremote.category.request.RequestCategoryTitleDto
import org.sopt.dataremote.category.response.toCoreModel
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class RemoteCategoryDataSourceImpl @Inject constructor(
  private val categoryService: CategoryService,
) : RemoteCategoryDataSource {
  override suspend fun getCategoryAll(): List<CategoryList> =
    categoryService.getCategoryAll().data!!.map {
      it.toCoreModel()
    }

  override suspend fun postAddCategoryTitle(categoryTitle: String): Int =
    categoryService.postAddCategoryTitle(
      RequestCategoryTitleDto(
        categoryTitle = categoryTitle,
      ),
    ).code
}
