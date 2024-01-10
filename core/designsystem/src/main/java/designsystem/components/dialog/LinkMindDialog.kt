package designsystem.components.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import designsystem.components.button.state.LinkMindButtonState
import org.sopt.mainfeature.databinding.DialogLinkmindBinding
import org.sopt.ui.view.caculateMarignDialog
import org.sopt.ui.view.onThrottleClick

class LinkMindDialog constructor(private val context: Context) {

  private val builder: AlertDialog.Builder by lazy {
    AlertDialog.Builder(context).setView(binding.root)
  }

  private val binding: DialogLinkmindBinding by lazy {
    DialogLinkmindBinding.inflate(LayoutInflater.from(context))
  }

  private var dialog: AlertDialog? = null
  fun show() {
    val dialogMarginWidth = caculateMarignDialog(37.0f)
    dialog = builder.create()
    dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
    dialog?.show()
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog?.window?.setLayout(dialogMarginWidth, WindowManager.LayoutParams.WRAP_CONTENT)
  }

  fun dismiss() {
    val parentViewGroup = binding.root.parent as? ViewGroup
    parentViewGroup?.removeView(binding.root)
    dialog?.dismiss()
  }

  fun setTitle(@StringRes messageId: Int): LinkMindDialog {
    binding.tvDialogTitle.apply {
      text = context.getText(messageId)
    }
    return this
  }

  fun setSubtitle(@StringRes messageId: Int?): LinkMindDialog {
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

  fun setCloseBtn() =
    binding.ivDialogCancel.onThrottleClick {
      dismiss()
    }

  fun setPositiveButton(
    @StringRes text: Int,
    onClickListener: () -> (Unit),
  ): LinkMindDialog {
    binding.btnPositive.apply {
      state = LinkMindButtonState.ENABLE
      setText(context.getText(text).toString())
      btnClick {
        onClickListener()
      }
    }
    return this
  }

  fun setNegativeButton(
    @StringRes text: Int,
    onClickListener: () -> (Unit) ,
  ): LinkMindDialog {
    binding.btnNegative.apply {
      state = LinkMindButtonState.DISABLE
      visibility = View.VISIBLE
      setText(context.getText(text).toString())
      btnClick {
        onClickListener()
      }
    }
    return this
  }

  fun create() {
    dialog = builder.create()
  }
}
