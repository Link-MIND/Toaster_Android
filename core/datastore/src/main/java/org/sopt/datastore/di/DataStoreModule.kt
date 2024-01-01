package org.sopt.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.datastore.datastore.SecurityDataStore
import org.sopt.datastore.datastore.generateSecurityDataStore
import tech.thdev.useful.encrypted.data.store.preferences.security.generateUsefulSecurity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
  private val Context.dataStore by preferencesDataStore(name = "linkmind_data_store")

  @Singleton
  @Provides
  fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
    return context.dataStore
  }

  @Singleton
  @Provides
  fun provideSecurityDataStore(dataStore: DataStore<Preferences>): SecurityDataStore {
    return dataStore.generateSecurityDataStore(
      generateUsefulSecurity(),
    )
  }
}
