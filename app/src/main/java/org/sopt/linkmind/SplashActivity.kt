package org.sopt.linkmind

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.sopt.login.onboarding.LoginActivity

class SplashActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    val intent = Intent(this, LoginActivity::class.java)
    startActivity(intent)
    finish()
  }
}
