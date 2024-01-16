package org.sopt.savelink.ui

data class LinkState(
  val edittextLink: String = "",
) {
  fun checkEtvLink() =
    edittextLink.contains("http")
}
sealed interface LinkSideEffect {
  data object NavigateUp : LinkSideEffect
  data object NavigateSetLink : LinkSideEffect
  data object ShowBottomSheet : LinkSideEffect
}
