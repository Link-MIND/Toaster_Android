package org.sopt.timer

import androidx.recyclerview.widget.RecyclerView
import org.sopt.model.timer.Timer
import org.sopt.timer.databinding.ItemTimerWaitBinding
import org.sopt.ui.view.onThrottleClick

class WaitTimerViewHolder(
  private val binding: ItemTimerWaitBinding,
  private val onToggleClicked: (Timer) -> Unit,
  private val onMoreClicked: (Timer) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

  fun onBind(data: Timer?) {
    if (data == null) return
    with(binding) {
      tvItemTimerWaitCategory.text = data.comment
      val time = data.remindTime.replace("AM", "오전").replace("PM", "오후")
      tvItemTimerWaitWhen.text = TIME_FORMAT.format(data.remindDates, time)
      vItemTimerWait.setOnClickListener {
        tgItemTimerWait.transition()
        onToggleClicked(data)
      }
      tgItemTimerWait.initToggleState(data.isAlarm!!)
      ivItemTimerWaitMore.onThrottleClick {
        onMoreClicked(data)
      }
    }
  }

  companion object {
    private const val TIME_FORMAT = "매주 %s %s마다"
  }
}
