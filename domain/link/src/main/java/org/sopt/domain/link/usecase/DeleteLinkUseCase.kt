package org.sopt.domain.link.usecase

import org.sopt.domain.link.repository.LinkRepository
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class DeleteLinkUseCase @Inject constructor(
  private val linkRepository: LinkRepository
) {
  suspend operator fun invoke(): Result<Int> = linkRepository.deleteLink()
}
