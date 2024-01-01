package org.sopt.oauthdomain.interactor

import org.sopt.oauthdomain.entity.KakaoToken

interface OAuthInteractor {
  suspend fun loginByKakao(): Result<KakaoToken>
  fun logout()
  fun withdraw()
}
