package designsystem.components.button

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import designsystem.components.button.state.LinkMindButtonFullWidthState
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.ButtonMediumWidthLinkmindBinding
import org.sopt.ui.view.onThrottleClick

class LinkMindButtonMediumWidth @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

  private val binding: ButtonMediumWidthLinkmindBinding

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
      clBtnMediumWidthLinkmind.isClickable = true
      clBtnMediumWidthLinkmind.isFocusable = true
      tvBtn.setTextColor(ContextCompat.getColor(context, R.color.black))
    }
  }

  private fun setBtnDisable() {
    binding.apply {
      clBtnMediumWidthLinkmind.isClickable = false
      clBtnMediumWidthLinkmind.isFocusable = false
      tvBtn.setTextColor(ContextCompat.getColor(context, R.color.black))
      clBtnMediumWidthLinkmind.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
    }
  }

  fun btnClick(onClick: () -> Unit) {
    binding.clBtnMediumWidthLinkmind.onThrottleClick {
      onClick()
    }
  }

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = ButtonMediumWidthLinkmindBinding.inflate(inflater, this, true)

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
      clBtnMediumWidthLinkmind.setBackgroundResource(background)
      tvBtn.text = text
      tvBtn.setTextColor(textColor)
    }

    typedArray.recycle()
  }
}
