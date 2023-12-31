package org.sopt.common.intentprovider

import android.content.Intent

interface IntentProvider {
  fun getAuthIntent(): Intent
}
