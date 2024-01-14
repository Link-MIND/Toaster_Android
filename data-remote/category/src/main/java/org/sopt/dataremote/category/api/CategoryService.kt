package org.sopt.dataremote.category.api

import org.sopt.dataremote.category.request.RequestCategoryTitleDto
import org.sopt.dataremote.category.response.ResponseCategoryEntireDto
import org.sopt.network.model.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryService {
  companion object {
    const val CATEGORY = "category"
    const val ALL = "all"
  }

  @GET("/$CATEGORY/$ALL")
  suspend fun getCategoryAll(): BaseResponse<ResponseCategoryEntireDto>

  @POST("/$CATEGORY")
  suspend fun postAddCategoryTitle(@Body categoryTitleDto: RequestCategoryTitleDto):
    BaseResponse<Int>
}
