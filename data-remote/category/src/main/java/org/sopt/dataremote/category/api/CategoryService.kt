package org.sopt.dataremote.category.api

import org.sopt.dataremote.category.request.RequestCategoryDeleteDTO
import org.sopt.dataremote.category.request.RequestCategoryEditTitleDTO
import org.sopt.dataremote.category.request.RequestCategoryPriorityDTO
import org.sopt.dataremote.category.request.RequestCategoryTitleDto
import org.sopt.dataremote.category.response.ResponseCategoryDuplicateDTO
import org.sopt.dataremote.category.response.ResponseCategoryEntireDto
import org.sopt.dataremote.category.response.ResponseLinksDTO
import org.sopt.network.model.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryService {
  companion object {
    const val CATEGORY = "category"
    const val ALL = "all"
    const val EDIT = "edit"
    const val CHECK = "check"
    const val CATEGORYID = "categoryId"
    const val TITLE = "title"
  }

  @GET("/$CATEGORY/$ALL")
  suspend fun getCategoryAll(): BaseResponse<ResponseCategoryEntireDto>

  @POST("/$CATEGORY")
  suspend fun postAddCategoryTitle(@Body categoryTitleDto: RequestCategoryTitleDto): BaseResponse<Int>

  @DELETE("/$CATEGORY")
  suspend fun deleteCategory(@Body deleteCategory: RequestCategoryDeleteDTO): BaseResponse<Unit>

  @GET("/$CATEGORY/$CHECK")
  suspend fun getCategoryDuplicate(
    @Query("title") title: String,
  ): BaseResponse<ResponseCategoryDuplicateDTO>

  @GET("/$CATEGORY/{$CATEGORYID}")
  suspend fun getCategoryLink(
    @Path(CATEGORYID) categoryId: Long?,
    @Query("filter") filter: String?,
  ): BaseResponse<ResponseLinksDTO>

  @PATCH("/$CATEGORY/$EDIT")
  suspend fun patchCategoryPriority(@Body requestBody: RequestCategoryPriorityDTO): BaseResponse<Unit>

  @PATCH("/$CATEGORY/$TITLE")
  suspend fun patchCategoryEdit(@Body requestBody: RequestCategoryEditTitleDTO): BaseResponse<Unit>
}
