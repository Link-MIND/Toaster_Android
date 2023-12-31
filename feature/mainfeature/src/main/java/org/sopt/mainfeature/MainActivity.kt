package org.sopt.mainfeature

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.sopt.mainfeature.databinding.ActivityMainBinding
import org.sopt.oauthdomain.interactor.OAuthInteractor
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  lateinit var binding: ActivityMainBinding

  @Inject
  lateinit var kakaoAuthInteractor: OAuthInteractor

  private val viewModel: MainViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.tvMain.setOnClickListener {
      lifecycleScope.launch {
        kakaoAuthInteractor.loginByKakao().onSuccess {
          viewModel.login()
        }.onFailure {
        }
      }
    }
  }
}
