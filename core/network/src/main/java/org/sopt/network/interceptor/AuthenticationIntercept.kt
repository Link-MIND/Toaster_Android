package org.sopt.network.interceptor

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.sopt.core.network.BuildConfig.BASE_URL
import org.sopt.datastore.datastore.SecurityDataStore
import javax.inject.Inject

class AuthenticationIntercept @Inject constructor(
  private val dataStore: SecurityDataStore,
) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val authRequest = handleRequest(originalRequest)
    return chain.proceed(authRequest)
  }

  private fun handleRequest(originalRequest: Request) =
    when {
      originalRequest.url.encodedPath.contains("auth") -> {
        when {
          originalRequest.url.encodedPath.contains("withdraw") || originalRequest.url.encodedPath.contains("out")  -> {
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
      else -> originalRequest.accessTokenBuilder()
    }

  private fun Request.accessTokenBuilder() =
    this.newBuilder().addHeader("accessToken", runBlocking { dataStore.flowAccessToken().first() }).build()

  private fun Request.refreshTokenBuilder() =
    this.newBuilder().addHeader("refreshToken", runBlocking { dataStore.flowRefreshToken().first() }).build()
}
