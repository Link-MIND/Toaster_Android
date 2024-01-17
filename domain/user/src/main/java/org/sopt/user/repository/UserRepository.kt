package org.sopt.user.repository

import org.sopt.model.user.MyPageData
import org.sopt.model.user.SettingPageData

interface UserRepository {
  suspend fun getUserMyPage(): Result<MyPageData>

  suspend fun getUserSetting(): Result<SettingPageData>

  suspend fun patchPush(allowedPush: Boolean): Result<Boolean>
}
