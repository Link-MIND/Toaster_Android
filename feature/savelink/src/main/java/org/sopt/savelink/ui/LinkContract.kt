package org.sopt.savelink.ui

import org.sopt.model.category.Category

data class LinkState(
  val edittextLink: String = "",
  val categoryList: List<Clip?> = emptyList()
) {
  fun checkEtvLink() =
    edittextLink.contains("http")
}
sealed interface LinkSideEffect {
  data object NavigateUp : LinkSideEffect
  data object NavigateSetLink : LinkSideEffect
  data object ShowBottomSheet : LinkSideEffect
}
