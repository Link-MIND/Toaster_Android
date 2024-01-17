package org.sopt.dataremote.category.datasource

import org.sopt.data.category.datasource.RemoteCategoryDataSource
import org.sopt.dataremote.category.api.CategoryService
import org.sopt.dataremote.category.request.RequestCategoryEditTitleDTO
import org.sopt.dataremote.category.request.RequestCategoryPriorityDTO
import org.sopt.dataremote.category.request.RequestCategoryTitleDto
import org.sopt.dataremote.category.response.toCoreModel
import org.sopt.model.category.CategoryDuplicate
import org.sopt.model.category.CategoryLinkList
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class RemoteCategoryDataSourceImpl @Inject constructor(
  private val categoryService: CategoryService,
) : RemoteCategoryDataSource {
  override suspend fun getCategoryAll(): CategoryList =
    categoryService.getCategoryAll().data!!.toCoreModel()

  override suspend fun postAddCategoryTitle(categoryTitle: String?): Int =
    categoryService.postAddCategoryTitle(
      RequestCategoryTitleDto(
        categoryTitle = categoryTitle,
      ),
    ).code

  override suspend fun deleteCategory(deleteCategoryList: Long) {
    categoryService.deleteCategory(
      deleteCategoryList,
    )
  }

  override suspend fun getCategoryDuplicate(title: String): CategoryDuplicate =
    requireNotNull(categoryService.getCategoryDuplicate(title = title).data).toCoreModel()

  override suspend fun getCategoryLink(filter: String?, categoryId: Long?): CategoryLinkList =
    requireNotNull(
      categoryService.getCategoryLink(
        categoryId = categoryId,
        filter = filter,

      ).data,
    ).toCoreModel()

  override suspend fun patchCategoryPriority(categoryId: Long, newPriority: Int) {
    requireNotNull(
      categoryService.patchCategoryPriority(
        RequestCategoryPriorityDTO(categoryId, newPriority),
      ),
    )
  }
  override suspend fun patchCategoryEditTitle(categoryId: Long, newTitle: String) {
    requireNotNull(
      categoryService.patchCategoryEdit(
        RequestCategoryEditTitleDTO(categoryId, newTitle),
      ),
    )
  }

  /*  override suspend fun patchCategoryEdit(categoryId: Long, newTitle: String?, newPriority: Long?) {
      val changeCategoryTitle=ChangeCategoryTitle(
        categoryId = categoryId,
        newTitle = requireNotNull(newTitle),
      )
      val changeCategoryPriority=
      ChangeCategoryPriority(
        categoryId = categoryId,
        newPriority = requireNotNull(newPriority),
      )
      categoryService.patchCategoryEdit(
        requestBody = Pair(changeCategoryTitle, changeCategoryPriority))
    }*/

/*  override suspend fun patchCategoryEdit(
    changeCategoryTitleList: List<CategoryChangeTitle>,
    changeCategoryPriorityList: List<CategoryChangePriority>,
  ) {*/
    /*   val changeCategoryTitle = ChangeCategoryTitle(
         categoryId = categoryId,
         newTitle = requireNotNull(newTitle),
       )
       val changeCategoryPriority =
         ChangeCategoryPriority(
           categoryId = categoryId,
           newPriority = requireNotNull(newPriority),
         )

       changeCategoryTitleList.toRequestDTO()*/
}
