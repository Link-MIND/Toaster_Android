package di

import api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.network.di.AuthLinkMindRetrofit
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserServiceModule {

  @Singleton
  @Provides
  fun provideUserService(@AuthLinkMindRetrofit retrofit: Retrofit): UserService =
    retrofit.create(UserService::class.java)
}
