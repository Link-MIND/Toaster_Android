package org.sopt.share

import org.sopt.share.model.Clip

data class ShareState(
  val categoryList: List<Clip> = emptyList(),
  val categoryId: Long? = 0,
  val url: String = "",
  val allClipCountNum: Long = 0,
)

sealed interface ShareSideEffect {

  sealed interface ShareActivitySideEffect : ShareSideEffect {
    data object DefinedUser : ShareActivitySideEffect

    data object UnDefinedUser : ShareActivitySideEffect
  }

  sealed interface ShareBottomSheetSideEffect : ShareSideEffect {
    data object SaveSuccess : ShareBottomSheetSideEffect
  }
}
