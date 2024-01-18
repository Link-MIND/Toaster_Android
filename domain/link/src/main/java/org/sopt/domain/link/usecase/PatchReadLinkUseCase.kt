package org.sopt.domain.link.usecase

import org.sopt.domain.link.repository.LinkRepository
import javax.inject.Inject

class PatchReadLinkUseCase @Inject constructor(
  private val linkRepository: LinkRepository,
) {
  suspend operator fun invoke(param: Param): Result<Boolean> = linkRepository.patchReadLink(
    toastId = param.toastId,
    isRead = param.isRead
  )

  data class Param(
    val toastId: Long,
    val isRead: Boolean
  )
}
