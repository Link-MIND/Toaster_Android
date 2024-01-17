package org.sopt.dataremote.category.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.category.CategoryLink
import org.sopt.model.category.CategoryLinkList

@Serializable
data class ResponseLinksDTO(
  @SerialName("allToastNum")
  val allToastNum: Long,
  @SerialName("toastListDto")
  val toastListDto: List<ToastDto>,
) {

  @Serializable
  data class ToastDto(
    @SerialName("toastId")
    val toastId: Long,
    @SerialName("categoryTitle")
    val categoryTitle: String?,
    @SerialName("isRead")
    val isRead: Boolean,
    @SerialName("linkUrl")
    val linkUrl: String,
    @SerialName("toastTitle")
    val toastTitle: String,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String?,
  )
}

internal fun ResponseLinksDTO.ToastDto.toCoreModel() = CategoryLink(
  toastId = toastId,
  categoryTitle = categoryTitle,
  isRead = isRead,
  linkUrl = linkUrl,
  toastTitle = toastTitle,
  thumbnailUrl = thumbnailUrl ?: "",
)

internal fun ResponseLinksDTO.toCoreModel() = CategoryLinkList(
  allToastNum = allToastNum,
  toastListDto = toastListDto.map { it.toCoreModel() },
)
