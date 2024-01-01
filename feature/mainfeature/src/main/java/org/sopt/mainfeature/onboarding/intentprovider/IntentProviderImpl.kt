package org.sopt.mainfeature.onboarding.intentprovider

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.mainfeature.onboarding.LoginActivity
import javax.inject.Inject

class IntentProviderImpl @Inject constructor(
  @ApplicationContext private val context: Context,
) : IntentProvider {
  override fun getAuthIntent(): Intent =
    LoginActivity.newInstance(context)
}
