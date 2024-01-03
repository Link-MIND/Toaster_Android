package designsystem.components.button

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import designsystem.components.button.state.LinkMindButtonFullWidthState
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.ButtonBlockLinkmindBinding
import org.sopt.ui.view.onThrottleClick

@SuppressLint("CustomViewStyleable")
class LinkMindBlockButton @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

  private val binding: ButtonBlockLinkmindBinding

  var state: LinkMindButtonFullWidthState = LinkMindButtonFullWidthState.ENABLE
    set(value) {
      field = value
      when (field) {
        LinkMindButtonFullWidthState.ENABLE -> {
          setBtnEnable(R.color.black)
        }

        LinkMindButtonFullWidthState.DISABLE -> {
          setBtnDisable(R.color.black)
        }
      }
    }

  private fun setBtnEnable(textColorResId: Int) {
    binding.apply {
      clBtnMediumWidthLinkmind.isClickable = true
      clBtnMediumWidthLinkmind.isFocusable = true
      tvBtn.setTextColor(ContextCompat.getColor(context, textColorResId))
    }
  }

  private fun setBtnDisable(textColorResId: Int) {
    binding.apply {
      clBtnMediumWidthLinkmind.isClickable = false
      clBtnMediumWidthLinkmind.isFocusable = false
      clBtnMediumWidthLinkmind.setBackgroundColor(ContextCompat.getColor(context, textColorResId))
    }
  }

  fun btnClick(onClick: () -> Unit) {
    binding.clBtnMediumWidthLinkmind.onThrottleClick {
      onClick()
    }
  }

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = ButtonBlockLinkmindBinding.inflate(inflater, this, true)

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
