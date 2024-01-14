package org.sopt.remote.home.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.home.RecommendLink

@Serializable
data class ResponseRecommendLinkDto(
  @SerialName("siteId")
  val siteId: Long,
  @SerialName("siteTitle")
  val siteTitle: String?,
  @SerialName("siteSub")
  val siteSub: String?,
  @SerialName("siteImg")
  val siteImg: String?,
  @SerialName("siteUrl")
  val siteUrl: String?,
)


internal fun ResponseRecommendLinkDto.toCoreModel() = RecommendLink(
  siteId = siteId,
  siteTitle = siteTitle,
  siteSub = siteSub,
  siteImg = siteImg,
  siteUrl = siteUrl,
)
