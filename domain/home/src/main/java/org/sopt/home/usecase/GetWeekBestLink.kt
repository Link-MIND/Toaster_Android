package org.sopt.home.usecase

import org.sopt.home.repository.HomeRepository
import org.sopt.model.home.WeekBestLink
import javax.inject.Inject

class GetWeekBestLink @Inject constructor(
  private val homeRepository: HomeRepository,
) {
  suspend operator fun invoke(): Result<List<WeekBestLink>> = homeRepository.getWeekBestLink()
}
