package org.sopt.savelink.ui.setlink

import org.sopt.savelink.ui.model.Clip

data class SaveLinkSetClipState(
  val categoryList: List<Clip> = emptyList(),
  val categoryId: Long? = 0,
  val url: String = "",
)

sealed interface SaveLinkSetClipSideEffect {
  data object NavigateSaveLinkSetClip : SaveLinkSetClipSideEffect
  data object NavigateUp : SaveLinkSetClipSideEffect
  data object ShowBottomSheet : SaveLinkSetClipSideEffect
  data object ShowDialog : SaveLinkSetClipSideEffect
}
