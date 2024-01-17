package org.sopt.user.usecase

import org.sopt.user.repository.UserRepository
import javax.inject.Inject

class GetUserSettingUseCase @Inject constructor(
  private val userRepository: UserRepository,
) {
  suspend operator fun invoke() = userRepository.getUserSetting()
}
