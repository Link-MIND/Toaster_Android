package org.sopt.user.repository

import org.sopt.model.user.MyPageData
import org.sopt.model.user.SettingPageData
import org.sopt.user.source.RemoteUserDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val remoteUserDataSource: RemoteUserDataSource) : UserRepository {
  override suspend fun getUserMyPage(): Result<MyPageData> =
    runCatching {
      remoteUserDataSource.getMyPage()
    }

  override suspend fun getUserSetting(): Result<SettingPageData> =
    runCatching {
      remoteUserDataSource.getSetting()
    }

  override suspend fun patchPush(allowedPush: Boolean): Result<Boolean> =
    runCatching {
      remoteUserDataSource.patchAllowedPush(allowedPush)
    }
}
