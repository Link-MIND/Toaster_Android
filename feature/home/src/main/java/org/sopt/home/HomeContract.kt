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
  val recommendLink: List<RecommendLink> = emptyList(),
  val url: String = "",
) {
  fun calculateProgress(): Int {
    if ( readToastNum > allToastNum) return 0
    if (allToastNum==0) return 0
      else return readToastNum / allToastNum * 100
  }
}

sealed interface HomeSideEffect {
  data object NavigateSetting : HomeSideEffect
  data object NavigateSearch : HomeSideEffect
  data object NavigateWebview : HomeSideEffect
  data object showBottomSheet : HomeSideEffect
}
