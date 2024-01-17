package org.sopt.user.usecase

import org.sopt.user.repository.UserRepository
import javax.inject.Inject

class GetUserMyPageUseCase @Inject constructor(
  private val userRepository: UserRepository,
) {
  suspend operator fun invoke() = userRepository.getUserMyPage()
}
