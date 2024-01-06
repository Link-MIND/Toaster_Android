package org.sopt.login.onboarding.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.login.onboarding.intentprovider.IntentProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class IntentModule {
  @Binds
  @Singleton
  abstract fun bindsIntentProvider(intentProvider: IntentProviderImpl): IntentProvider
}
