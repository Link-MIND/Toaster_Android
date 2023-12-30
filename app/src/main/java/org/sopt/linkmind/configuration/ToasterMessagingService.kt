package org.sopt.linkmind.configuration

import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.datastore.datastore.SecurityDataStore
import javax.inject.Inject

@AndroidEntryPoint
class ToasterMessagingService : FirebaseMessagingService() {

  @Inject
  lateinit var dataStore: SecurityDataStore

  override fun onNewToken(token: String) {
    super.onNewToken(token)

    CoroutineScope(Dispatchers.IO).launch { dataStore.setDeviceToken(token) }
  }
}
