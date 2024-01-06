package org.sopt.mainfeature

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.bottomsheet.LinkMindBottomSheet
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import designsystem.components.button.state.LinkMindButtonState
import designsystem.components.dialog.LinkMindDialog
import org.sopt.mainfeature.databinding.ActivityDesignComponentsBinding
import timber.log.Timber

@AndroidEntryPoint
class ExamDesignActivity : AppCompatActivity() {
  private lateinit var binding: ActivityDesignComponentsBinding

  private val linkMindDialog by lazy {
    LinkMindDialog(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityDesignComponentsBinding.inflate(layoutInflater)

    binding.progress.apply {
      setProgressBarMain(63)
    }
    binding.btnBlock.apply {
      state = LinkMindButtonState.ENABLE
      btnClick {
      }
    }
    binding.etv.apply {
      onClickTextClear {
        Timber.d("SaK")
      }
      throttleAfterTextChanged {
        Timber.d("SaK")
      }
    }

    binding.btnBlock2.apply {
      state = LinkMindButtonState.DISABLE
    }
    binding.btnFull.apply {
      state = LinkMIndFullWidthButtonState.ENABLE_PRIMARY
    }
    binding.btnFull1.apply {
      state = LinkMIndFullWidthButtonState.ENABLE_BLACK
    }
    binding.btnFull2.apply {
      state = LinkMIndFullWidthButtonState.DISABLE
    }
    setContentView(binding.root)
    showRevokeCommonDialog()

    val linkMindBottomSheet = LinkMindBottomSheet(this)
    linkMindBottomSheet.show()
    linkMindBottomSheet.apply {
      setTitle(R.string.text_clip)
      setErroMsg(R.string.text_clip)
      bottomSheetConfirmBtnClick {
        Log.d("test", "test")
      }
    }
  }

  private fun showRevokeCommonDialog() {
    linkMindDialog.setTitle(R.string.text_home)
      .setSubtitle(R.string.text_clip)
      .setNegativeButton(R.string.text_home) {
        linkMindDialog.dismiss()
      }
      .setPositiveButton(R.string.text_home) {
        linkMindDialog.dismiss()
      }
      .show()
  }
}
