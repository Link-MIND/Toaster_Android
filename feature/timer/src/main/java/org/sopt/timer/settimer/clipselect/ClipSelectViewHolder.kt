package org.sopt.timer.settimer.clipselect

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.sopt.timer.databinding.ItemTimerClipSelectBinding
import org.sopt.timer.dummymodel.Clip

class ClipSelectViewHolder(
  val binding: ItemTimerClipSelectBinding,
  val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: Clip?, onClick: (Clip, Int) -> Unit) {
    if (data == null) return
    with(binding) {
      tvItemTimerClipName.text = data.name
      tvItemTimerClipCount.text = "${data.count}개"
      val selectedColor = ContextCompat.getColor(context, org.sopt.mainfeature.R.color.primary)
      val defaultColor = ContextCompat.getColor(context, org.sopt.mainfeature.R.color.neutrals900)
      if (data.isSelected) {
        tvItemTimerClipCount.setTextColor(selectedColor)
        tvItemTimerClipName.setTextColor(selectedColor)
      } else {
        tvItemTimerClipCount.setTextColor(defaultColor)
        tvItemTimerClipName.setTextColor(defaultColor)
      }
      root.setOnClickListener {
        onClick(data, bindingAdapterPosition)
      }
    }
  }
}
