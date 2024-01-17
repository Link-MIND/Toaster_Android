package di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import datasource.RemoteUserDataSourceImpl
import org.sopt.user.source.RemoteUserDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataSourceModule {
  @Singleton
  @Binds
  abstract fun bindRemoteLectureProviderDatasource(
    remoteUserDataSource: RemoteUserDataSourceImpl,
  ): RemoteUserDataSource
}
