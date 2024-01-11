package org.sopt.domain.link.usecase

import org.sopt.domain.link.repository.LinkRepository
import javax.inject.Inject

class PostSaveLinkUseCase @Inject constructor(
  private val linkRepository: LinkRepository,
) {
  suspend operator fun invoke(): Result<Int> = linkRepository.postSaveLink()
}
