package org.sopt.oauthdomain.repository

import org.sopt.oauthdomain.entity.KakaoToken

interface OAuthRepository {
  suspend fun loginByKakao(): Result<KakaoToken>
  fun logout()
  fun withdraw()
}
