package org.sopt.maincontainer

import org.sopt.home.HomeSideEffect

data class MainState(
  val isBottomNavigationBarVisible: Boolean = true,
  val clipboard: String = "",
)

sealed interface MainSideEffect{
  data object NavigateSaveLink : MainSideEffect
}
