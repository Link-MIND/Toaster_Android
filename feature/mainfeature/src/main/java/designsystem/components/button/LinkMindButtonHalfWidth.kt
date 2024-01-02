package designsystem.components.button

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import designsystem.components.button.state.LinkMindButtonFullWidthState
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.ButtonHalfWidthLinkmindBinding
import org.sopt.ui.view.onThrottleClick

@SuppressLint("CustomViewStyleable")
class LinkMindButtonHalfWidth @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

  private val binding: ButtonHalfWidthLinkmindBinding

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
      clBtnHalfWidthLinkmind.isClickable = true
      clBtnHalfWidthLinkmind.isFocusable = true
      tvBtn.setTextColor(ContextCompat.getColor(context, R.color.black))
    }
  }

  private fun setBtnDisable() {
    binding.apply {
      clBtnHalfWidthLinkmind.isClickable = false
      clBtnHalfWidthLinkmind.isFocusable = false
      tvBtn.setTextColor(ContextCompat.getColor(context, R.color.black))
      clBtnHalfWidthLinkmind.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
    }
  }

  fun btnClick(onClick: () -> Unit) {
    binding.clBtnHalfWidthLinkmind.onThrottleClick {
      onClick()
    }
  }
  fun setText(text:String) {
    binding.tvBtn.text=text
  }

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = ButtonHalfWidthLinkmindBinding.inflate(inflater, this, true)

    val typedArray =
      context.obtainStyledAttributes(attrs, R.styleable.LinkMindButtonHalfWidth, defStyleAttr, 0)

    val background = typedArray.getResourceId(
      R.styleable.LinkMindButtonHalfWidth_HalfWidthBtnBackGroundTint,
      R.drawable.ripple_btn,
    )
    val text = typedArray.getText(R.styleable.LinkMindButtonHalfWidth_HalfWidthBtnText)
    val textColor = typedArray.getColor(
      R.styleable.LinkMindButtonHalfWidth_WidthBtnTextColor,
      ContextCompat.getColor(context, R.color.black),
    )

    binding.apply {
      clBtnHalfWidthLinkmind.setBackgroundResource(background)
      tvBtn.text = text
      tvBtn.setTextColor(textColor)
    }

    typedArray.recycle()
  }
}
