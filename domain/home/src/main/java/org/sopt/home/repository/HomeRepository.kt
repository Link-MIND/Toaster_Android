package org.sopt.home.repository

import org.sopt.model.home.MainPage
import org.sopt.model.home.RecommendLink
import org.sopt.model.home.WeekBestLink

interface HomeRepository {
  suspend fun getMainPageUserClip(): Result<MainPage>
  suspend fun getRecommendSite(): Result<List<RecommendLink>>
  suspend fun getWeekBestLink(): Result<List<WeekBestLink>>

}
