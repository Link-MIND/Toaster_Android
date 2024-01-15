package org.sopt.timer

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import org.sopt.timer.databinding.FragmentNotificationPermissionDialogBinding
import org.sopt.ui.base.BindingDialogFragment
import org.sopt.ui.view.onThrottleClick

class NotificationPermissionDialogFragment : BindingDialogFragment<FragmentNotificationPermissionDialogBinding>(
  { FragmentNotificationPermissionDialogBinding.inflate(it) },
) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.ivNotiPermissionDialogClose.onThrottleClick {
      dismiss()
    }

    binding.btnNotificationPermissionDialog.onThrottleClick {
      navigateToNotificationSetting(requireContext())
      dismiss()
    }
  }

  private fun navigateToNotificationSetting(context: Context) {
    val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      setNotificationIntentActionOreo(context)
    } else {
      setNorificationIntentActionOreoLess(context)
    }
    context.startActivity(intent)
  }

  private fun setNotificationIntentActionOreo(context: Context): Intent {
    return Intent().also { intent ->
      intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
      intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
  }

  private fun setNorificationIntentActionOreoLess(context: Context): Intent {
    return Intent().also { intent ->
      intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
      intent.putExtra("app_package", context.packageName)
      intent.putExtra("app_uid", context.applicationInfo?.uid)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
  }
}
