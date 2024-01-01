package org.sopt.oauthdata.interactor

import android.content.Context
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.suspendCancellableCoroutine
import org.sopt.oauthdomain.entity.KakaoToken
import org.sopt.oauthdomain.interactor.OAuthInteractor
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class KakaoAuthInteractor @Inject constructor(
  private val client: UserApiClient,
  @ActivityContext private val context: Context,
) : OAuthInteractor {
  override suspend fun loginByKakao(): Result<KakaoToken> =
    suspendCancellableCoroutine {
      when (client.isKakaoTalkLoginAvailable(context)) {
        true -> {
          client.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
              it.resume(Result.failure(error))
              return@loginWithKakaoTalk
            }
            if (token != null) {
              it.resume(Result.success(KakaoToken(token.accessToken, token.refreshToken)))
              return@loginWithKakaoTalk
            }
            it.resumeWithException(Throwable("Unreachable code"))
          }
        }
        false -> {
          client.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
              it.resume(Result.failure(error))
              return@loginWithKakaoAccount
            }
            if (token != null) {
              it.resume(Result.success(KakaoToken(token.accessToken, token.refreshToken)))
              return@loginWithKakaoAccount
            }
            it.resumeWithException(Throwable("Unreachable code"))
          }
        }
      }
    }

  override fun logout() {
    client.logout(Timber::e)
  }

  override fun withdraw() {
    client.unlink(Timber::e)
  }
}
