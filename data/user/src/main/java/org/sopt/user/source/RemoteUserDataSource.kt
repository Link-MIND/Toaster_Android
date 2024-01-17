package org.sopt.user.source

import org.sopt.model.user.MyPageData
import org.sopt.model.user.SettingPageData

interface RemoteUserDataSource {
  suspend fun getMyPage(): MyPageData
  suspend fun getSetting(): SettingPageData
  suspend fun patchAllowedPush(allowedPush: Boolean): Boolean
}
