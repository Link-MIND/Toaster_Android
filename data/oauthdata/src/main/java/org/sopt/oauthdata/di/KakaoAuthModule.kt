package org.sopt.oauthdata.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import org.sopt.oauthdata.repository.KakaoAuthRepository
import org.sopt.oauthdomain.repository.OAuthRepository

@Module
@InstallIn(ActivityComponent::class)
object KakaoAuthModule {
  @Provides
  @ActivityScoped
  fun provideKakaoAuthRepository(
    kakaoAuthRepository: KakaoAuthRepository,
  ): OAuthRepository = kakaoAuthRepository
}
