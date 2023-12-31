package org.sopt.mainfeature.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.sopt.mainfeature.databinding.ActivityLoginBinding
import org.sopt.oauthdomain.interactor.OAuthInteractor
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
  private lateinit var binding: ActivityLoginBinding

  @Inject
  private lateinit var kakaoAuthInteractor: OAuthInteractor
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnKakaoLogin.setOnClickListener {
      lifecycleScope.launch {
        kakaoAuthInteractor.loginByKakao()
          .onSuccess {
            Log.e("소셜 액세스토큰", it.accessToken)
            Log.e("소셜 리프레쉬토큰", it.accessToken)
          }.onFailure {
            Log.e("실패", "실패")
          }
      }
    }
  }

  companion object {
    @JvmStatic
    fun newInstance(context: Context) = Intent(context, LoginActivity::class.java).apply {
      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
  }
}
