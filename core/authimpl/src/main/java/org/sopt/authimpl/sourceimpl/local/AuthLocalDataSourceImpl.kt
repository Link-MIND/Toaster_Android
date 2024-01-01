package org.sopt.authimpl.sourceimpl.local

import kotlinx.coroutines.flow.first
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

  override suspend fun getToken(): Token =
    Token(
      dataStore.flowAccessToken().first(),
      dataStore.flowRefreshToken().first(),
      dataStore.flowDeviceToken().first()
    )
  override suspend fun clear() {
    dataStore.clearAll()
  }
}
