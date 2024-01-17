package org.sopt.dataremote.category.api

import org.sopt.dataremote.category.response.ResponseSearchDto
import org.sopt.network.model.response.base.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
  @GET("/main/search")
  suspend fun getSearchResult(@Query("query") query: String): BaseResponse<ResponseSearchDto>
}
