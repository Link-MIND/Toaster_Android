package org.sopt.user.usecase

import org.sopt.user.repository.UserRepository
import javax.inject.Inject

class PatchPushUseCase @Inject constructor(
  private val userRepository: UserRepository,
) {
  suspend operator fun invoke(allowedPush: Boolean) = userRepository.patchPush(allowedPush)
}
