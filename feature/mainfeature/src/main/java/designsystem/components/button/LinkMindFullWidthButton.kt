package designsystem.components.button

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import designsystem.components.button.state.LinkMindButtonFullWidthState
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.ButtonFullWidthLinkmindBinding
import org.sopt.ui.view.onThrottleClick

class LinkMindFullWidthButton @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

  private val binding: ButtonFullWidthLinkmindBinding

  var state: LinkMindButtonFullWidthState = LinkMindButtonFullWidthState.ENABLE
    set(value) {
      field = value
      when (field) {
        LinkMindButtonFullWidthState.ENABLE -> {
          setBtnEnable()
        }

        LinkMindButtonFullWidthState.DISABLE -> {
          setBtnDisable()
        }
      }
    }

  private fun setBtnEnable() {
    binding.apply {
      clBtnFullWidthLinkmind.isClickable = true
      clBtnFullWidthLinkmind.isFocusable = true
    }
  }

  private fun setBtnDisable() {
    binding.apply {
      clBtnFullWidthLinkmind.isClickable = false
      clBtnFullWidthLinkmind.isFocusable = false
      clBtnFullWidthLinkmind.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
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
