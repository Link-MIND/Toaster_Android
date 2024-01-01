package org.sopt.network.interceptor

import android.content.Context
import com.jakewharton.processphoenix.ProcessPhoenix
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.datastore.datastore.SecurityDataStore
import javax.inject.Inject

class AuthenticationIntercept @Inject constructor(
  private val dataStore: SecurityDataStore,
  @ApplicationContext private val context: Context,
  private val intentProvider: IntentProvider,
) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val authRequest = handleRequest(originalRequest)
    val response = chain.proceed(authRequest)
    if (response.code == CODE_INVALID_USER) {
      runBlocking { dataStore.setAutoLogin(false) }
      ProcessPhoenix.triggerRebirth(context, intentProvider.getAuthIntent())
    }
    response.close()
    return chain.proceed(authRequest)
  }

  private fun handleRequest(originalRequest: Request) =
    when {
      originalRequest.url.encodedPath.contains("auth") -> {
        when {
          originalRequest.url.encodedPath.contains("withdraw") || originalRequest.url.encodedPath.contains("out") -> {
            originalRequest.accessTokenBuilder()
          }

          originalRequest.url.encodedPath.contains("token") -> {
            originalRequest.refreshTokenBuilder()
          }

          else -> {
            originalRequest
          }
        }
      }

      else -> {
        originalRequest.accessTokenBuilder()
      }
    }

  private fun Request.accessTokenBuilder() =
    this.newBuilder().addHeader("accessToken", runBlocking { dataStore.flowAccessToken().first() }).build()

  private fun Request.refreshTokenBuilder() =
    this.newBuilder().addHeader("refreshToken", runBlocking { dataStore.flowRefreshToken().first() }).build()

  companion object {
    private const val CODE_INVALID_USER = 404
  }
}
