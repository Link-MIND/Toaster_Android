package org.sopt.savelink.ui.savelink

import org.sopt.savelink.ui.model.Clip

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
  data object ShowDialog : LinkSideEffect
}
