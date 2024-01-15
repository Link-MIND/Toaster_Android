package org.sopt.home.usecase

import org.sopt.home.repository.HomeRepository
import org.sopt.model.home.RecommendLink
import javax.inject.Inject

class GetRecommendSite @Inject constructor(
  private val homeRepository: HomeRepository,
) {
  suspend operator fun invoke(): Result<List<RecommendLink>> = homeRepository.getRecommendSite()
}
