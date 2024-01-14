package org.sopt.remote.home.api

import org.sopt.network.model.response.base.BaseResponse
import org.sopt.remote.home.response.ResponseMainPageDto
import org.sopt.remote.home.response.ResponseRecommendLinkDto
import org.sopt.remote.home.response.ResponseWeekBestLinkDto
import retrofit2.http.GET

interface HomeService {
  companion object {
    const val USER = "user"
    const val MAIN = "main"
    const val SITES = "sites"
    const val TOAST = "toast"
    const val WEEK = "week"
  }

  @GET("/$USER/$MAIN")
  suspend fun getMainUserClip(): BaseResponse<ResponseMainPageDto>

  @GET("/$SITES")
  suspend fun getRecommendSites(): BaseResponse<List<ResponseRecommendLinkDto>>

  @GET("/$TOAST/$WEEK")
  suspend fun getWeekBestLink(): BaseResponse<List<ResponseWeekBestLinkDto>>
}
