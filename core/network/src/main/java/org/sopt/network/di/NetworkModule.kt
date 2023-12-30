package org.sopt.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import org.sopt.core.network.BuildConfig.BASE_URL
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  @Provides
  @Singleton
  @NoneOkHttpClient
  fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
  ): OkHttpClient =
    OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build()

  @Provides
  @Singleton
  fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor { message ->
      when {
        message.isJsonObject() ->
          Timber.d("Retrofit2", JSONObject(message).toString(4))

        message.isJsonArray() ->
          Timber.d("Retrofit2", JSONArray(message).toString(4))

        else -> {
          Timber.d("Retrofit2", "CONNECTION INFO -> $message")
        }
      }
    }
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return loggingInterceptor
  }

  @Singleton
  @Provides
  @LinkMindRetrofit
  fun provideLinkMindRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()
}

private fun String?.isJsonObject(): Boolean = this?.startsWith("{") == true && this.endsWith("}")
private fun String?.isJsonArray(): Boolean = this?.startsWith("[") == true && this.endsWith("]")
