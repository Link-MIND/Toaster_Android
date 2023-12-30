package org.sopt.datastore.datastore

import kotlinx.coroutines.flow.Flow
import tech.thdev.useful.encrypted.data.store.preferences.ksp.annotations.UsefulPreferences
import tech.thdev.useful.encrypted.data.store.preferences.ksp.annotations.value.GetValue
import tech.thdev.useful.encrypted.data.store.preferences.ksp.annotations.value.SetValue

@UsefulPreferences
interface SecurityDataStore {
  @GetValue(KEY_ACCESSTOKEN)
  fun flowAccessToken(): Flow<String>

  @SetValue(KEY_ACCESSTOKEN)
  suspend fun setAccessToken(string: String)

  @GetValue(KEY_REFRESHTOKEN)
  fun flowRefreshToken(): Flow<String>

  @SetValue(KEY_REFRESHTOKEN)
  suspend fun setRefreshToken(string: String)

  companion object {
    const val KEY_ACCESSTOKEN = "key-accesstoken"
    const val KEY_REFRESHTOKEN = "key-refreshtoken"
  }
}
