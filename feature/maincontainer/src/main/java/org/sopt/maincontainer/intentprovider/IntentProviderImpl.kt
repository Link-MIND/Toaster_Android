package org.sopt.maincontainer.intentprovider

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.maincontainer.MainActivity
import javax.inject.Inject

class IntentProviderImpl @Inject constructor(
  @ApplicationContext private val context: Context,
) : IntentProvider {
  override fun getIntent(): Intent =
    MainActivity.newInstance(context)
}
