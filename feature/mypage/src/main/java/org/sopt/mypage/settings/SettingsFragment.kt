package org.sopt.mypage.settings

import android.os.Bundle
import android.view.View
import org.sopt.mypage.databinding.FragmentSettingsBinding
import org.sopt.ui.base.BindingFragment

class SettingsFragment : BindingFragment<FragmentSettingsBinding>({ FragmentSettingsBinding.inflate(it) }) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val toasterToggle = binding.settingsAlertToggle
    val tvSettingsAlertOff = binding.tvSettingsAlertOff

    val startStateId = org.sopt.mainfeature.R.id.start

    updateVisibility(tvSettingsAlertOff, toasterToggle.getState(), startStateId)

    toasterToggle.btnClick {
      updateVisibility(tvSettingsAlertOff, toasterToggle.getState(), startStateId)
    }
  }

  private fun updateVisibility(view: View, state: Int, startStateId: Int) {
    view.visibility = if (state == startStateId) View.VISIBLE else View.INVISIBLE
  }
}
