package org.sopt.mainfeature

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber

class LinkMindApp : Application() {
  override fun onCreate() {
    super.onCreate()
    setTimber()
    setDarkMode()
  }

  private fun setTimber() {
    Timber.plant(Timber.DebugTree())
  }

  private fun setDarkMode() {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
  }
}
