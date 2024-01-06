package org.sopt.mypage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.mainfeature.R
import org.sopt.mypage.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

  private lateinit var binding: FragmentSettingsBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    binding = FragmentSettingsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val toasterToggle = binding.settingsAlertToggle
    val tvSettingsAlertOff = binding.tvSettingsAlertOff

    tvSettingsAlertOff.visibility =
      if (toasterToggle.getState() == R.id.start) View.VISIBLE else View.INVISIBLE

    toasterToggle.btnClick {
      tvSettingsAlertOff.visibility =
        if (toasterToggle.getState() == R.id.start) View.VISIBLE else View.INVISIBLE
    }
  }
}
