package org.sopt.mainfeature

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import org.sopt.login.onboarding.LoginActivity

class SplashActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val intent = Intent(this, org.sopt.login.onboarding.LoginActivity::class.java)
    startActivity(intent)
    finish()
  }
}
