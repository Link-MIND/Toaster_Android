package org.sopt.network.authenticator

import android.content.Context
import android.util.Log
import com.jakewharton.processphoenix.ProcessPhoenix
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.datastore.datastore.SecurityDataStore
import org.sopt.network.service.TokenRefreshService
import javax.inject.Inject

class LinkMindAuthenticator @Inject constructor(
  private val dataStore: SecurityDataStore,
  private val tokenRefreshService: TokenRefreshService,
  @ApplicationContext private val context: Context,
  private val intentProvider: IntentProvider,
) : Authenticator {
  override fun authenticate(route: Route?, response: Response): Request? {
    if (response.code == CODE_TOKEN_EXPIRED) {
      val newTokens = runCatching {
        runBlocking {
          tokenRefreshService.postAuthRefresh()
        }
      }.onSuccess {
        runBlocking {
          dataStore.apply {
            setAccessToken(it.data?.accessToken ?: "")
            setRefreshToken(it.data?.refreshToken ?: "")
          }
        }
      }.onFailure {
        runBlocking {
          dataStore.clearAll()
        }
        ProcessPhoenix.triggerRebirth(context, intentProvider.getAuthIntent())
      }.getOrThrow()

      return response.request.newBuilder()
        .header("accessToken", newTokens.data?.accessToken ?: "")
        .build()
    }
    return null
  }
  companion object{
    const val CODE_TOKEN_EXPIRED = 401
  }
}
