package org.sopt.savelink.ui.setlink

import org.sopt.savelink.ui.Clip

data class SetLinkState(
  val categoryList: List<Clip> = emptyList(),
  val categoryId: Long? = 0,
  val url:String = ""
)

sealed interface SetLinkSideEffect {
  data object NavigateSetLink : SetLinkSideEffect
  data object NavigateUp : SetLinkSideEffect
  data object ShowBottomSheet : SetLinkSideEffect
  data object ShowDialog : SetLinkSideEffect
}
