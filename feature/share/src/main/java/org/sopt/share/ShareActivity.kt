package org.sopt.share

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.lifecycle.lifecycleScope
import com.jakewharton.processphoenix.ProcessPhoenix
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.common.intentprovider.LOGIN
import org.sopt.datastore.datastore.SecurityDataStore
import org.sopt.share.databinding.ActivityShareBinding
import org.sopt.share.databinding.ActivityShareBinding.inflate
import org.sopt.ui.base.BindingActivity
import javax.inject.Inject

@AndroidEntryPoint
class ShareActivity : AppCompatActivity() {
  private val viewModel by viewModels<ShareViewModel>()
  lateinit var clipData: ClipData
  var isBottomSheetCreated: Boolean = false

  @LOGIN
  @Inject
  lateinit var intentProvider: IntentProvider

  @Inject
  lateinit var dataStore: SecurityDataStore


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    getUrl()
    viewModel.observe(this, sideEffect = ::handleSideEffect)
  }

  private fun getUrl() {
    val action: String = intent.action ?: ""
    val type: String? = intent.type

    if (Intent.ACTION_SEND == action && type != null) {
      if ("text/plain" == type) {
        val url: String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        viewModel.updateUrl(url ?: "")
        clipData = ClipData.newPlainText("url", url)
      }
    }
  }

  private fun handleSideEffect(sideEffect: ShareSideEffect) {
    when (sideEffect) {
      ShareSideEffect.DefinedUser -> {
        if (!isBottomSheetCreated) {
          isBottomSheetCreated = true
          ShareBottomSheetFragment.newInstance {
            finish()
          }.show(supportFragmentManager, "share")
        }
      }

      ShareSideEffect.UnDefinedUser -> {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.setPrimaryClip(clipData)
        lifecycleScope.launch {
          runCatching { dataStore.setAutoLogin(false) }
            .onSuccess {
              ProcessPhoenix.triggerRebirth(this@ShareActivity, intentProvider.getIntent())
            }
          finish()
        }
      }

      else -> {}
    }
  }
}
