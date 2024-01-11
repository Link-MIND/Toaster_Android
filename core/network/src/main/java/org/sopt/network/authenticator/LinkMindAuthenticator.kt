package org.sopt.network.authenticator

import android.content.Context
import com.jakewharton.processphoenix.ProcessPhoenix
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.common.intentprovider.LOGIN
import org.sopt.datastore.datastore.SecurityDataStore
import org.sopt.network.service.TokenRefreshService
import retrofit2.HttpException
import javax.inject.Inject

class LinkMindAuthenticator @Inject constructor(
  private val dataStore: SecurityDataStore,
  private val tokenRefreshService: TokenRefreshService,
  @ApplicationContext private val context: Context,
  @LOGIN private val intentProvider: IntentProvider,
) : Authenticator {
  override fun authenticate(route: Route?, response: Response): Request? {
    if (response.code == CODE_TOKEN_EXPIRED) {
      val newTokens = runCatching {
        runBlocking {
          tokenRefreshService.postAuthRefresh(dataStore.flowRefreshToken().first())
        }
      }.onSuccess {
        if(it.code == CODE_TOKEN_EXPIRED) {
          runBlocking {
            dataStore.setAutoLogin(false)
          }
          ProcessPhoenix.triggerRebirth(context, intentProvider.getIntent())
          return@onSuccess
        }
        runBlocking {
          dataStore.apply {
            setAccessToken(it.data?.accessToken ?: "")
            setRefreshToken(it.data?.refreshToken ?: "")
          }
        }
      }.onFailure {
        runBlocking {
          dataStore.setAutoLogin(false)
        }
        ProcessPhoenix.triggerRebirth(context, intentProvider.getIntent())
      }.getOrThrow()

      return response.request.newBuilder()
        .header("accessToken", newTokens.data?.accessToken ?: "")
        .build()
    }
    return null
  }

  companion object {
    const val CODE_TOKEN_EXPIRED = 401
  }
}
