package designsystem.components.button

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.ButtonFullWidthLinkmindBinding
import org.sopt.ui.view.onThrottleClick

@SuppressLint("CustomViewStyleable")
class LinkMindFullWidthButton @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

  private val binding: ButtonFullWidthLinkmindBinding

  var state: LinkMIndFullWidthButtonState = LinkMIndFullWidthButtonState.ENABLE_PRIMARY
    set(value) {
      field = value
      when (field) {
        LinkMIndFullWidthButtonState.ENABLE_PRIMARY -> {
          setBtnEnable(R.color.primary)
        }

        LinkMIndFullWidthButtonState.ENABLE_BLACK -> {
          setBtnEnable(R.color.neutrals_black)
        }

        LinkMIndFullWidthButtonState.DISABLE -> {
          setBtnDisable(R.color.neutrals100)
        }
      }
    }
  private fun setBtnEnable(textColorResId: Int) {
    binding.apply {
      clBtnFullWidthLinkmind.isClickable = true
      clBtnFullWidthLinkmind.isFocusable = true
      tvBtn.setTextColor(ContextCompat.getColor(context, R.color.neutrals_white))
      clBtnFullWidthLinkmind.setBackgroundColor(ContextCompat.getColor(context, textColorResId))
    }
  }

  private fun setBtnDisable(textColorResId: Int) {
    binding.apply {
      clBtnFullWidthLinkmind.isClickable = false
      clBtnFullWidthLinkmind.isFocusable = false
      tvBtn.setTextColor(ContextCompat.getColor(context, R.color.neutrals_white))
      clBtnFullWidthLinkmind.setBackgroundColor(ContextCompat.getColor(context, textColorResId))
    }
  }

  fun setBackGround(backgroundResource: Int) {
    binding.apply {
      clBtnFullWidthLinkmind.isClickable = true
      clBtnFullWidthLinkmind.isFocusable = true
      tvBtn.setTextColor(ContextCompat.getColor(context, R.color.neutrals_white))
      clBtnFullWidthLinkmind.setBackgroundResource(backgroundResource)
    }
  }

  fun btnClick(onClick: () -> Unit) {
    binding.clBtnFullWidthLinkmind.onThrottleClick {
      onClick()
    }
  }

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = ButtonFullWidthLinkmindBinding.inflate(inflater, this, true)

    val typedArray =
      context.obtainStyledAttributes(attrs, R.styleable.LinkMindButtonFullWidth, defStyleAttr, 0)

    val background = typedArray.getResourceId(
      R.styleable.LinkMindButtonFullWidth_fullWidthBtnBackGroundTint,
      R.drawable.ripple_btn,
    )
    val text = typedArray.getText(R.styleable.LinkMindButtonFullWidth_fullWidthBtnText)
    val textColor = typedArray.getColor(
      R.styleable.LinkMindButtonFullWidth_fullWidthBtnTextColor,
      ContextCompat.getColor(context, R.color.black),
    )
    binding.apply {
      clBtnFullWidthLinkmind.setBackgroundResource(background)
      tvBtn.text = text
      tvBtn.setTextColor(textColor)
    }

    typedArray.recycle()
  }
}
