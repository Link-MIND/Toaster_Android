package org.sopt.mainfeature.timer

import androidx.recyclerview.widget.RecyclerView
import org.sopt.mainfeature.databinding.ItemTimerCompleteBinding
import org.sopt.mainfeature.databinding.ItemTimerWaitBinding
import org.sopt.mainfeature.timer.dummymodel.Timer

class WaitTimerViewHolder(
  private val binding: ItemTimerWaitBinding,
  private val onToggleClicked: (Timer) -> Unit,
  private val onMoreClicked: (Timer) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: Timer?) {
    if (data == null) return

    binding.tvItemTimerWaitCategory.text = data.category
    val minute = if(data.minute == 0) "" else " ${data.minute}분"
    binding.tvItemTimerWaitWhen.text = "매주 " + data.day + " 오전 " + data.hour.toString() +"시" + minute + "마다"

    binding.root.setOnClickListener {
      onToggleClicked(data)
    }
    binding.root.setOnClickListener {
      onMoreClicked(data)
    }
  }
}
