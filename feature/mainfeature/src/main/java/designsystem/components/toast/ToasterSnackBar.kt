package designsystem.components.toast

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.LayoutToasterSnackbarBinding

fun Context.toasterSnackBar(view: View, message: String, warning: Boolean = false) {
  Snackbar.make(view, "", Snackbar.LENGTH_LONG).apply {
    val snackbarBinding = LayoutToasterSnackbarBinding.inflate(LayoutInflater.from(this@toasterSnackBar))

    (this.view as Snackbar.SnackbarLayout).apply {
      removeAllViews()
      setPadding(0, 0, 0, 0)
      setBackgroundColor(ContextCompat.getColor(this@toasterSnackBar, android.R.color.transparent))

      if (warning) snackbarBinding.ivToast.setImageResource(R.drawable.ic_alert_18_white)
      snackbarBinding.tvToast.text = message

      addView(snackbarBinding.root, 0)
    }

    snackbarBinding.root.layoutParams = FrameLayout.LayoutParams(
      FrameLayout.LayoutParams.WRAP_CONTENT,
      FrameLayout.LayoutParams.WRAP_CONTENT,
    ).apply {
      gravity = Gravity.CENTER
    }

    show()
  }
}
