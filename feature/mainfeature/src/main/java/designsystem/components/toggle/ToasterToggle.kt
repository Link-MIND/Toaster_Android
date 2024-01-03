package designsystem.components.toggle

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.ToggleLinkmindBinding

class ToasterToggle @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
  private val binding: ToggleLinkmindBinding

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = ToggleLinkmindBinding.inflate(inflater, this, true)
  }

  fun initToggleState(boolean: Boolean) {
    when (boolean) {
      true -> {
        binding.mlToggle.transitionToState(R.id.end, 1)
      }

      false -> {
        binding.mlToggle.transitionToState(R.id.start, 1)
      }
    }
  }

  fun btnClick() {
    when (binding.mlToggle.currentState) {
      R.id.start -> {
        binding.mlToggle.transitionToState(R.id.end, 200)
      }

      R.id.end -> {
        binding.mlToggle.transitionToState(R.id.start, 200)
      }
    }
  }

  fun getState() = binding.mlToggle.currentState
}
