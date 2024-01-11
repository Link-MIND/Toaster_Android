package org.sopt.domain.link.usecase

import org.sopt.domain.link.repository.LinkRepository
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class DeleteLinkUseCase @Inject constructor(
  private val linkRepository: LinkRepository
) {
  suspend operator fun invoke(param:Param): Result<Int> = linkRepository.deleteLink(
    toastId = param.toastId
  )

  data class Param(
    val toastId: Long,
  )
}
