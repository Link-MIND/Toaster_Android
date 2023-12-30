package org.sopt.ui.intent

import android.content.Intent
import android.os.Build
import android.os.Parcelable

fun <T : Parcelable> Intent.getParcelable(key: String, c: Class<T>): T? = when {
  Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelableExtra(key, c)
  else -> getParcelableExtra(key) as? T
}
