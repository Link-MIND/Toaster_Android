package org.sopt.home

import org.sopt.model.category.Category
import org.sopt.model.home.RecommendLink
import org.sopt.model.home.WeekBestLink

data class HomeState(
  val nickName: String = "",
  val readToastNum: Int = 0,
  val allToastNum: Int = 0,
  val categoryList: List<Category?> = emptyList(),
  val weekBestLink: List<WeekBestLink> = emptyList(),
  val recommendLink: List<RecommendLink> = emptyList()
  )

sealed interface HomeSideEffect {
  data object NavigateSetting : HomeSideEffect
  data object NavigateSearcg : HomeSideEffect
  data object showBottomSheet : HomeSideEffect
}