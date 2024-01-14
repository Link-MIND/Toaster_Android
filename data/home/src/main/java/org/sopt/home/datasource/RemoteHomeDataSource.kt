package org.sopt.home.datasource

import org.sopt.model.home.MainPage
import org.sopt.model.home.RecommendLink
import org.sopt.model.home.WeekBestLink

interface RemoteHomeDataSource {
  suspend fun getMainPageUserClip(): MainPage
  suspend fun getRecommendSite(): List<RecommendLink>
  suspend fun getWeekBestLink(): List<WeekBestLink>
}
