package designsystem.components.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
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

  fun setTitle(message: CharSequence): LinkMindDialog {
    binding.tvDialogTitle.apply {
      text = message
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

  fun setDescription(message: CharSequence): LinkMindDialog {
    binding.tvDialogSubtitle.apply {
      if (message.isEmpty()) {
        visibility = View.GONE
      } else {
        visibility = View.VISIBLE
        text = message
      }
    }
    return this
  }

  fun setGoneDescription(): LinkMindDialog {
    binding.tvDialogSubtitle.visibility = View.GONE
    return this
  }

  fun setPositiveButton(
    text: CharSequence,
    onClickListener: (view: View) -> (Unit),
  ): LinkMindDialog {
    binding.btnPositive.apply {
      setText(text.toString())
      setOnClickListener(onClickListener)
      dismiss()
    }
    return this
  }

  fun setNegativeButton(
    text: CharSequence,
    onClickListener: (view: View) -> (Unit) = {},
  ): LinkMindDialog {
    binding.btnPositive.apply {
      visibility = View.VISIBLE
      setText(text.toString())
      setOnClickListener(onClickListener)
    }
    return this
  }

  fun create() {
    dialog = builder.create()
  }

  fun show() {
    dialog = builder.create()
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
    dialog?.show()
  }

  fun dismiss() {
    val parentViewGroup = binding.root.parent as? ViewGroup
    parentViewGroup?.removeView(binding.root)
    dialog?.dismiss()
  }
}
