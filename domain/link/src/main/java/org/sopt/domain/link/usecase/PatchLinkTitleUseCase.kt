package org.sopt.domain.link.usecase

import org.sopt.domain.link.repository.LinkRepository
import javax.inject.Inject

class PatchLinkTitleUseCase @Inject constructor(
  private val linkRepository: LinkRepository,
) {
  suspend operator fun invoke(param: Param): Result<String> = linkRepository.patchLinkTitle(
    toastId = param.toastId,
    title = param.title,
  )

  data class Param(
    val toastId: Long,
    val title: String,
  )
}
