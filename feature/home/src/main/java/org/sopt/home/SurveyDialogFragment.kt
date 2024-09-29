package org.sopt.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import org.sopt.home.databinding.FragmentSurveyDialogBinding
import org.sopt.ui.base.BindingDialogFragment
import org.sopt.ui.view.onThrottleClick

class SurveyDialogFragment : BindingDialogFragment<FragmentSurveyDialogBinding>(
  { FragmentSurveyDialogBinding.inflate(it) },
) {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NO_FRAME, android.R.style.Theme_Dialog)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.ivSurveyDialogClose.onThrottleClick {
      dismiss()
    }

    binding.btnSurveyDialog.onThrottleClick {
      dismiss()
    }

    binding.btnSurveyDialogSkip.onThrottleClick {
      dismiss()
    }
  }

  override fun onStart() {
    super.onStart()
    val dialog = dialog
    if (dialog != null) {
      dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
  }
}
