package org.sopt.savelink.ui

data class SetLinkState(
  val categoryList: List<Clip> = emptyList(),
  val categoryId: Long? = 0,
  val url:String = ""
) {

}
sealed interface SetLinkSideEffect {
  data object NavigateSetLink : SetLinkSideEffect
  data object ShowBottomSheet : SetLinkSideEffect

  data object ShowDialog : SetLinkSideEffect
}
