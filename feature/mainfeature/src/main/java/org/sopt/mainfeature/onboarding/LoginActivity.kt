package org.sopt.mainfeature.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.dialog.LinkMindDialog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.datastore.datastore.SecurityDataStore
import org.sopt.mainfeature.MainActivity
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.ActivityLoginBinding
import org.sopt.oauthdomain.interactor.OAuthInteractor
import org.sopt.ui.context.toast
import org.sopt.ui.view.UiState
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
  private lateinit var binding: ActivityLoginBinding
  private val viewModel: LoginViewModel by viewModels()

  @Inject
  lateinit var kakaoAuthInteractor: OAuthInteractor

  @Inject
  lateinit var dataStore: SecurityDataStore

  private val linkMindDialog by lazy {
    LinkMindDialog(this)
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)
    initCheckAutoLogin()
    initKakaoLoginBtnClickListener()
    initAuthStateObserver()
    showRevokeCommonDialog()

  }
  private fun showRevokeCommonDialog() {
    linkMindDialog.setTitle(R.string.text_home)
      .setDescription(R.string.text_clip)
      .setNegativeButton(R.string.text_home) {
        linkMindDialog.dismiss()
      }
      .setPositiveButton(R.string.text_home) {
        linkMindDialog.dismiss()
      }
      .show()
  }
  private fun initCheckAutoLogin() {
    lifecycleScope.launch {
      if (dataStore.flowAutoLogin().first()) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
      }
    }
  }

  private fun initAuthStateObserver() {
    viewModel.authState.flowWithLifecycle(lifecycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          when (state.data.isRegistered) {
            true -> {
              dataStore.setAutoLogin(true)
              val intent = Intent(this@LoginActivity, MainActivity::class.java)
              intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
              startActivity(intent)
            }

            false -> {
              val intent = Intent(this@LoginActivity, MainActivity::class.java)
              intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
              startActivity(intent)
            }
          }
        }

        is UiState.Failure -> {
          this@LoginActivity.toast(state.msg)
        }
        is UiState.Loading -> {}
        else -> {}
      }
    }.launchIn(lifecycleScope)
  }

  private fun initKakaoLoginBtnClickListener() {
    binding.btnKakaoLogin.setOnClickListener {
      lifecycleScope.launch {
        kakaoAuthInteractor.loginByKakao()
          .onSuccess {
            viewModel.authenticate(it.accessToken, "KAKAO")
          }.onFailure {
            this@LoginActivity.toast(it.message.toString())
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
