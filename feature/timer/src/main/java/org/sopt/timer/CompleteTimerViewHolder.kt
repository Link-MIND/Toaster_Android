package org.sopt.timer

import androidx.recyclerview.widget.RecyclerView
import org.sopt.timer.databinding.ItemTimerCompleteBinding
import org.sopt.timer.dummymodel.Timer

class CompleteTimerViewHolder(
  private val binding: ItemTimerCompleteBinding,
  private val onClicked: (Timer) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: Timer?) {
    if (data == null) return
    with(binding) {
      val ampm = if (data.am) AM else PM
      val minute = if (data.minute >= 10) data.minute.toString() else MINUTE_FORMAT.format(data.minute)
      tvItemTimerCompleteCategory.text = data.category
      tvItemTimerCompleteTime.text = TIME_FORMAT.format(data.day, ampm, data.hour, minute)
      tvItemTimerCompleteRead.setOnClickListener {
        onClicked(data)
      }
    }
  }

  companion object {
    private const val TIME_FORMAT = "%s %s %d:%s"
    private const val MINUTE_FORMAT = "0%d"
    private const val AM = "오전"
    private const val PM = "오후"
  }
}
