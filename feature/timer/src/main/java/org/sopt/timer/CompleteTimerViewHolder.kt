package org.sopt.timer

import androidx.recyclerview.widget.RecyclerView
import org.sopt.model.timer.Timer
import org.sopt.timer.databinding.ItemTimerCompleteBinding

class CompleteTimerViewHolder(
  private val binding: ItemTimerCompleteBinding,
  private val onClicked: (Timer) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: Timer?) {
    if (data == null) return
    with(binding) {
      tvItemTimerCompleteCategory.text = data.comment
      val time = data.remindTime.replace("AM", "오전").replace("PM", "오후")
      tvItemTimerCompleteTime.text = "${data.remindDate} $time"
      tvItemTimerCompleteRead.setOnClickListener {
        onClicked(data)
      }
    }
  }
}
