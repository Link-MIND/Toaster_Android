package org.sopt.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.core.network.BuildConfig.BASE_URL
import org.sopt.network.authenticator.LinkMindAuthenticator
import org.sopt.network.interceptor.AuthenticationIntercept
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  @Provides
  @Singleton
  @NoneAuthOkHttpClient
  fun provideOkHttpClient(
    @Logging loggingInterceptor: HttpLoggingInterceptor,
  ): OkHttpClient =
    OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build()

  @Provides
  @Singleton
  @AuthOkHttpClient
  fun provideAuthOkHttpClient(
    @Logging loggingInterceptor: HttpLoggingInterceptor,
    @Auth authInterceptor: Interceptor,
    authenticator: LinkMindAuthenticator,
  ): OkHttpClient =
    OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .addInterceptor(authInterceptor)
      .authenticator(authenticator)
      .build()

  @Provides
  @Singleton
  @Logging
  fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }

  @Provides
  @Singleton
  @Auth
  fun provideAuthInterceptor(interceptor: AuthenticationIntercept): Interceptor = interceptor

  @Singleton
  @Provides
  @LinkMindRetrofit
  fun provideLinkMindRetrofit(@NoneAuthOkHttpClient okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

  @Singleton
  @Provides
  @AuthLinkMindRetrofit
  fun provideAuthLinkMindRetrofit(@AuthOkHttpClient okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()
}
