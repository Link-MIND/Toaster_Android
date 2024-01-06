package org.sopt.mainfeature.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

  private lateinit var binding: ActivitySettingsBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivitySettingsBinding.inflate(layoutInflater)
    setContentView(binding.root)

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
