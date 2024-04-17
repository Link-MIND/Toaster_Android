package org.sopt.share

import org.sopt.share.model.Clip

data class ShareState(
  val categoryList: List<Clip> = emptyList(),
  val categoryId: Long? = 0,
  val url: String = "",
  val allClipCountNum: Long = 0,
)

sealed interface ShareSideEffect {
  data object SaveSuccess: ShareSideEffect

  data object DefinedUser: ShareSideEffect

  data object UnDefinedUser: ShareSideEffect
}


