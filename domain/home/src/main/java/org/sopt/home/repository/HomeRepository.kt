package org.sopt.home.repository

import org.sopt.model.home.MainPageData
import org.sopt.model.home.RecommendLink
import org.sopt.model.home.WeekBestLink

interface HomeRepository {
  suspend fun getMainPageUserClip(): Result<MainPageData>
  suspend fun getRecommendSite(): Result<List<RecommendLink>>
  suspend fun getWeekBestLink(): Result<List<WeekBestLink>>
}
