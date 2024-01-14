package org.sopt.remote.home.datasource

import android.util.Log
import org.sopt.home.datasource.RemoteHomeDataSource
import org.sopt.model.home.MainPageData
import org.sopt.model.home.RecommendLink
import org.sopt.model.home.WeekBestLink
import org.sopt.remote.home.api.HomeService
import org.sopt.remote.home.response.toCoreModel
import javax.inject.Inject

class RemoteHomeDataSourceImpl @Inject constructor(
  private val homeService: HomeService,
) : RemoteHomeDataSource {
  override suspend fun getMainPageUserClip(): MainPageData {
    Log.d("getMainPageRemote","${homeService.getMainUserClip().data!!}")
    return homeService.getMainUserClip().data!!.toCoreModel()
  }

  override suspend fun getRecommendSite(): List<RecommendLink> {
    Log.d("getRecommendSiteRemote","${homeService.getRecommendSites().data!!.map { it.toCoreModel() }}")
    return homeService.getRecommendSites().data!!.map { it.toCoreModel() }

  }

  override suspend fun getWeekBestLink(): List<WeekBestLink> {
    Log.d("getWeekBestLinRemote","${homeService.getWeekBestLink().data!!.map { it.toCoreModel() }}}")
   return homeService.getWeekBestLink().data!!.map { it.toCoreModel() }}
}
