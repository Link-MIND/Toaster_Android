package org.sopt.maincontainer

data class MainState(
  val isBottomNavigationBarVisible: Boolean = true,
  val clipboard: String = "",
)

sealed interface MainSideEffect {
  data object NavigateSaveLink : MainSideEffect
}
