package org.sopt.mainfeature.timer

import androidx.recyclerview.widget.RecyclerView
import org.sopt.mainfeature.databinding.ItemTimerWaitBinding
import org.sopt.mainfeature.timer.dummymodel.Timer

class WaitTimerViewHolder(
  private val binding: ItemTimerWaitBinding,
  private val onToggleClicked: (Timer) -> Unit,
  private val onMoreClicked: (Timer) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

  fun onBind(data: Timer?) {
    if (data == null) return
    with(binding) {
      tvItemTimerWaitCategory.text = data.category
      val ampm = if (data.am) AM else PM
      val minute = if(data.minute != 0) MINUTE_FORMAT.format(data.minute) else ""
      tvItemTimerWaitWhen.text = TIME_FORMAT.format(data.day, ampm, data.hour, minute)

      tgItemTimerWait.setOnClickListener {
        onToggleClicked(data)
      }
      ivItemTimerWaitMore.setOnClickListener {
        onMoreClicked(data)
      }
    }
  }

  companion object {
    private const val TIME_FORMAT = "매주 %s %s %d시%s마다"
    private const val MINUTE_FORMAT = " %d분"
    private const val AM = "오전"
    private const val PM = "오후"
  }
}
