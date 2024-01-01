package org.sopt.oauthdata.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import org.sopt.oauthdata.interactor.KakaoAuthInteractor
import org.sopt.oauthdomain.interactor.OAuthInteractor

@Module
@InstallIn(ActivityComponent::class)
object KakaoAuthModule {
  @Provides
  @ActivityScoped
  fun provideKakaoAuthRepository(
    kakaoAuthInteractor: KakaoAuthInteractor,
  ): OAuthInteractor = kakaoAuthInteractor
}
