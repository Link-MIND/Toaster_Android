package designsystem.components.button

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import designsystem.components.button.state.LinkMindButtonFullWidthState
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.ButtonPopUpLinkmindBinding
import org.sopt.ui.view.onThrottleClick

@SuppressLint("CustomViewStyleable")
class LinkMindPopUpButton @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

  private val binding: ButtonPopUpLinkmindBinding

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
      tvBtn.setTextColor(ContextCompat.getColor(context, R.color.white))
      clBtnHalfWidthLinkmind.setBackgroundResource(R.drawable.shape_primary_fill_8_rect)
    }
  }

  private fun setBtnDisable() {
    binding.apply {
      clBtnHalfWidthLinkmind.isClickable = false
      clBtnHalfWidthLinkmind.isFocusable = false
      tvBtn.setTextColor(ContextCompat.getColor(context, R.color.neutrals400))
      clBtnHalfWidthLinkmind.setBackgroundResource(R.drawable.shape_neutrals_fill_8_rect)
    }
  }

  fun btnClick(onClick: () -> Unit) {
    binding.clBtnHalfWidthLinkmind.onThrottleClick {
      onClick()
    }
  }

  fun setText(text: String) {
    binding.tvBtn.text = text
  }

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = ButtonPopUpLinkmindBinding.inflate(inflater, this, true)

    val typedArray =
      context.obtainStyledAttributes(attrs, R.styleable.LinkMindPopUpButton, defStyleAttr, 0)

    val background = typedArray.getResourceId(
      R.styleable.LinkMindPopUpButton_PopUpBtnBackGroundTint,
      R.drawable.ripple_btn,
    )
    val text = typedArray.getText(R.styleable.LinkMindPopUpButton_PopUpBtnText)
    val textColor = typedArray.getColor(
      R.styleable.LinkMindPopUpButton_PopUpBtnTextColor,
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
