package designsystem.components.horizontalprogressbar

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import org.sopt.mainfeature.databinding.ProgressBarLinkmindBinding

class LinkMindHorizontalProgressBar @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
  private val binding: ProgressBarLinkmindBinding

  fun setProgressBarMain(percentage: Int) =
    ObjectAnimator.ofInt(binding.progressBarExp, "progress", percentage)
      .setDuration(500)
      .start()

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = ProgressBarLinkmindBinding.inflate(inflater, this, true)
  }
}
