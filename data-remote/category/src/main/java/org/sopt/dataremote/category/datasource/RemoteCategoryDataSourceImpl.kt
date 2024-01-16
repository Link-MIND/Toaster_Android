package org.sopt.dataremote.category.datasource

import org.sopt.data.category.datasource.RemoteCategoryDataSource
import org.sopt.dataremote.category.api.CategoryService
import org.sopt.dataremote.category.request.RequestCategoryTitleDto
import org.sopt.dataremote.category.request.toRequestDTO
import org.sopt.dataremote.category.response.toCoreModel
import org.sopt.model.category.CategoryChangePriority
import org.sopt.model.category.CategoryChangeTitle
import org.sopt.model.category.CategoryDuplicate
import org.sopt.model.category.CategoryLinkList
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

  override suspend fun getCategoryDuplicate(title: String): CategoryDuplicate =
    requireNotNull(categoryService.getCategoryDuplicate(title = title).data).toCoreModel()

  override suspend fun getCategoryLink(filter: String): CategoryLinkList =
    requireNotNull(
      categoryService.getCategoryLink(
        filter = filter,
      ).data,
    ).toCoreModel()


  override suspend fun patchCategoryEdit() {

  }
}
