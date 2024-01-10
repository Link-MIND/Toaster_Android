package org.sopt.timer.settimer.repeat

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.timer.R
import org.sopt.timer.databinding.ItemTimerRepeatBinding
import org.sopt.timer.dummymodel.Repeat
import org.sopt.ui.view.onThrottleClick

class TimerRepeatViewHolder(
  val binding: ItemTimerRepeatBinding,
  val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: Repeat?, onClick: (Repeat, Int) -> Unit) {
    if (data == null) return
    with(binding) {
      root.onThrottleClick {
        data.isSelected = !data.isSelected
        onClick(data, bindingAdapterPosition)
        bindingAdapter?.notifyItemChanged(bindingAdapterPosition)
      }
      tvTimerRepeatDay.text = data.period
      val selectedColor = ContextCompat.getColor(context, org.sopt.mainfeature.R.color.primary)
      val defaultColor = ContextCompat.getColor(context, org.sopt.mainfeature.R.color.neutrals900)
      if (data.isSelected) {
        tvTimerRepeatDay.setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_bold_16)
        tvTimerRepeatDay.setTextColor(selectedColor)
        ivTimerRepeatCheck.isVisible = true
      } else {
        tvTimerRepeatDay.setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_medium_16)
        tvTimerRepeatDay.setTextColor(defaultColor)
        ivTimerRepeatCheck.isGone = true
      }
    }
  }
}
