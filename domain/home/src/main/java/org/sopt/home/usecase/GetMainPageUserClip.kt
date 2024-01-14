package org.sopt.home.usecase

import org.sopt.home.repository.HomeRepository
import org.sopt.model.home.MainPage
import javax.inject.Inject

class GetMainPageUserClip @Inject constructor(
  private val homeRepository: HomeRepository,
) {
  suspend operator fun invoke(): Result<MainPage> = homeRepository.getMainPageUserClip()
}
