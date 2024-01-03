package org.sopt.mainfeature.timer

import androidx.recyclerview.widget.RecyclerView
import org.sopt.mainfeature.databinding.ItemTimerCompleteBinding
import org.sopt.mainfeature.timer.dummymodel.Timer

class CompleteTimerViewHolder(
  private val binding: ItemTimerCompleteBinding,
  private val onClicked: (Timer) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: Timer?) {
    if (data == null) return
    val ampm = if(data.am)" 오전 " else " 오후 "
    val minute = if(data.minute < 10)"0${data.minute}" else data.minute.toString()
    binding.tvItemTimerCompleteCategory.text = data.category
    binding.tvItemTimerCompleteTime.text = data.day + ampm + data.hour.toString() + ":" + minute
    binding.tvItemTimerCompleteRead.setOnClickListener {
      onClicked(data)
    }
  }
}
