package designsystem.components.toggle

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.motion.widget.MotionLayout
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.ToggleLinkmindBinding
import org.sopt.ui.view.onThrottleClick

class ToasterToggle @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : MotionLayout(context, attrs, defStyleAttr) {
  private val binding: ToggleLinkmindBinding

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = ToggleLinkmindBinding.inflate(inflater, this, true)
  }

  fun initToggleState(boolean: Boolean) {
    binding.mlToggle.setTransitionDuration(0)
    when (boolean) {
      true -> {
        binding.mlToggle.transitionToState(R.id.end, 1)
      }

      false -> {
        binding.mlToggle.transitionToState(R.id.start, 1)
      }
    }
  }

  fun btnClick(onClick: () -> Unit) {
    binding.mlToggle.onThrottleClick {
      Log.e("클릭", "클릭")
      when (binding.mlToggle.currentState) {
        R.id.start -> {
          binding.mlToggle.transitionToState(R.id.end, 200)
        }

        R.id.end -> {
          binding.mlToggle.transitionToState(R.id.start, 200)
        }
      }
      onClick()
    }
  }

  fun transition() {
    when (binding.mlToggle.currentState) {
      R.id.start -> {
        binding.mlToggle.transitionToState(R.id.end, 200)
      }

      R.id.end -> {
        binding.mlToggle.transitionToState(R.id.start, 200)
      }
    }
  }

  fun transitionTo(boolean: Boolean) {
    when (boolean) {
      true -> binding.mlToggle.transitionToState(R.id.end)
      false -> binding.mlToggle.transitionToState(R.id.start)
    }
  }

  fun getState() = binding.mlToggle.currentState
}
