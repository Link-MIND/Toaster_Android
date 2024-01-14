package org.sopt.home.repository

import org.sopt.home.datasource.RemoteHomeDataSource
import org.sopt.model.home.MainPage
import org.sopt.model.home.RecommendLink
import org.sopt.model.home.WeekBestLink
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
  private val remoteHomeDataSource: RemoteHomeDataSource,
) : HomeRepository {
  override suspend fun getMainPageUserClip(): Result<MainPage> =
    runCatching { remoteHomeDataSource.getMainPageUserClip() }

  override suspend fun getRecommendSite(): Result<List<RecommendLink>> =
    runCatching { remoteHomeDataSource.getRecommendSite() }

  override suspend fun getWeekBestLink(): Result<List<WeekBestLink>> =
    runCatching { remoteHomeDataSource.getWeekBestLink() }

}
