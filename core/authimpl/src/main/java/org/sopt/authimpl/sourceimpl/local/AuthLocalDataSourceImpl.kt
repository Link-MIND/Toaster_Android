package org.sopt.authimpl.sourceimpl.local

import org.sopt.auth.model.Token
import org.sopt.authimpl.source.local.AuthLocalDataSource
import org.sopt.datastore.datastore.SecurityDataStore
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
  private val dataStore: SecurityDataStore,
) : AuthLocalDataSource {
  override suspend fun save(token: Token) {
    dataStore.apply {
      setAccessToken(token.accessToken)
      setRefreshToken(token.refreshToken)
      setDeviceToken(token.deviceToken)
    }
  }

  override suspend fun clear() {
    dataStore.clearAll()
  }
}
