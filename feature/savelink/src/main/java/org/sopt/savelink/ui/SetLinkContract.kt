package org.sopt.savelink.ui

import org.sopt.model.category.Category

data class SetLinkState(
  val categoryList: List<Clip> = emptyList()
) {
}
sealed interface SetLinkSideEffect {
  data object NavigateSetLink : SetLinkSideEffect
  data object ShowBottomSheet : SetLinkSideEffect
}
