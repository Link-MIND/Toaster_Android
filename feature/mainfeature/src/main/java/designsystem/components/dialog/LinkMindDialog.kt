package designsystem.components.dialog

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import org.sopt.mainfeature.databinding.DialogLinkmindBinding

class LinkMindDialog constructor(private val context: Context) {

  private val builder: AlertDialog.Builder by lazy {
    AlertDialog.Builder(context).setView(binding.root)
  }

  private val binding: DialogLinkmindBinding by lazy {
    DialogLinkmindBinding.inflate(LayoutInflater.from(context))
  }

  private var dialog: AlertDialog? = null

  fun setTitle(@StringRes messageId: Int): LinkMindDialog {
    binding.tvDialogTitle.apply {
      text = context.getText(messageId)
    }
    return this
  }

  fun setDescription(@StringRes messageId: Int?): LinkMindDialog {
    binding.tvDialogSubtitle.apply {
      if (messageId == null) {
        visibility = View.GONE
      } else {
        visibility = View.VISIBLE
        text = context.getText(messageId)
      }
    }
    return this
  }

  fun visibleSubtitle(): LinkMindDialog {
    binding.tvDialogSubtitle.visibility = View.GONE
    return this
  }

  fun setPositiveButton(
    @StringRes text: Int,
    onClickListener: (view: View) -> (Unit),
  ): LinkMindDialog {
    binding.btnPositive.apply {
      setText(context.getText(text).toString())
      setOnClickListener(onClickListener)
      dismiss()
    }
    return this
  }

  fun setNegativeButton(
    @StringRes text: Int,
    onClickListener: (view: View) -> (Unit) = {},
  ): LinkMindDialog {
    binding.btnNegative.apply {
      visibility = View.VISIBLE
      setText(context.getText(text).toString())
      setOnClickListener(onClickListener)
    }
    return this
  }

  fun create() {
    dialog = builder.create()
  }

  fun show() {
    val dpToPixel = Resources.getSystem().displayMetrics.density
    val dialogHorizontalMarginInPixels =
      (dpToPixel * 37.0f + 0.5f).toInt()
    val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
    dialog = builder.create()
    dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
    dialog?.show()
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog?.window?.setLayout(deviceWidth - 2 * dialogHorizontalMarginInPixels, WindowManager.LayoutParams.WRAP_CONTENT )

  }
  fun dismiss() {
    val parentViewGroup = binding.root.parent as? ViewGroup
    parentViewGroup?.removeView(binding.root)
    dialog?.dismiss()
  }
}
