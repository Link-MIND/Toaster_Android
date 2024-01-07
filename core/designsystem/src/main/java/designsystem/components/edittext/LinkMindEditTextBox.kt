package designsystem.components.edittext

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import org.sopt.common.util.throttleValue.ThrottleValue
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.EditTextBoxLinkmindBinding
import org.sopt.ui.view.onThrottleClick

@SuppressLint("CustomViewStyleable")
class LinkMindEditTextBox @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
  private val binding: EditTextBoxLinkmindBinding
  private val editThrottleValue = ThrottleValue<String>()
  val editText
    get() = binding.editText

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = EditTextBoxLinkmindBinding.inflate(inflater, this, true)

    binding.editText.setOnFocusChangeListener { _, hasFocus ->
      if (isPreventLosingFocus) return@setOnFocusChangeListener
      if (hasFocus) {
        preventFocusClearedByAdjustResize()
      }
    }
    binding.editText.doAfterTextChanged { text ->
      binding.editText.setTextAppearance(R.style.Typography_suit_medium_16)
      binding.ivCancel.visibility =
        if (text.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
    }
    val typedArray =
      context.obtainStyledAttributes(attrs, R.styleable.EditTextSearch, defStyleAttr, 0)

    val hint = typedArray.getText(R.styleable.EditTextSearch_editTextSearchHint)

    binding.editText.hint = hint

    typedArray.recycle()
  }

  fun checkTextLength(value:Int)=
    binding.editText.text.length >= value
  fun onClickTextClear(onClick: () -> Unit) {
    binding.ivCancel.onThrottleClick {
      binding.editText.text.clear()
      onClick()
    }
  }

  /**
   * 실제 사용하면서 사이드 효과 측정할게염
   * **/
  fun throttleAfterTextChanged(onClickListener: () -> Unit) {
    binding.editText.doAfterTextChanged { text ->
      val isTextEmpty = text.isNullOrEmpty()

      binding.ivCancel.visibility = if (isTextEmpty) View.INVISIBLE else View.VISIBLE

      if (!isTextEmpty) {
        editThrottleValue.setDelay(text.toString(), 300L) {
          onClickListener()
        }
      }
    }
  }

  private var isPreventLosingFocus = false

  private fun preventFocusClearedByAdjustResize() {
    isPreventLosingFocus = true
    val losingFocusDelay = 250L

    binding.editText.postDelayed(
      {
        binding.editText.run {
          if (isFocused.not()) {
            requestFocus()
          }
        }
        isPreventLosingFocus = false
      },
      losingFocusDelay,
    )
  }
}
