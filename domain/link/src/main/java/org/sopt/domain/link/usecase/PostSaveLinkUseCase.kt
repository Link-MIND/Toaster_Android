package org.sopt.domain.link.usecase

import org.sopt.domain.link.repository.LinkRepository
import javax.inject.Inject

class PostSaveLinkUseCase @Inject constructor(
  private val linkRepository: LinkRepository,
) {
  suspend operator fun invoke(param: Param): Result<Int> = linkRepository.postSaveLink(
    linkUrl = param.linkUrl,
    categoryId = param.categoryId,
  )

  data class Param(
    val linkUrl: String,
    val categoryId: Long,
  )
}
