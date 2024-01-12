package org.sopt.dataremote.category.api

import org.sopt.dataremote.category.response.ResponseCategoryDto
import org.sopt.network.model.response.base.BaseResponse
import retrofit2.http.GET

interface CategoryService {
  companion object {
    const val CATEGORY = "category"
    const val ALL = "all"
  }

  @GET("/$CATEGORY/$ALL")
  suspend fun getCategoryAll(): BaseResponse<List<ResponseCategoryDto>>
}