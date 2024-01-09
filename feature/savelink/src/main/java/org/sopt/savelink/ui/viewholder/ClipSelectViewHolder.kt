package org.sopt.savelink.ui.viewholder

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.sopt.savelink.databinding.ItemTimerClipSelectBinding
import org.sopt.savelink.ui.Clip

class ClipSelectViewHolder(
  val binding: ItemTimerClipSelectBinding,
  val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: Clip?, position: Int, onClick: (Clip, Int) -> Unit) {
    if (data == null) return
    setImageResource(position)
    setTexts(data)
    setTextColor(data, position)
    setOnClickListener(data, onClick)
  }
  private fun setImageResource(position: Int) {
    val imageResource = if (position == 0) {
      org.sopt.mainfeature.R.drawable.ic_clip_all_24
    } else {
      org.sopt.mainfeature.R.drawable.ic_clip_24
    }
    binding.ivItemTimerClip.setImageResource(imageResource)
  }

  private fun setTexts(data: Clip) {
    with(binding) {
      tvItemTimerClipName.text = data.name
      tvItemTimerClipCount.text = "${data.count}개"
    }
  }

  private fun setTextColor(data: Clip, position: Int) {
    val colorResource = if (data.isSelected) {
      setClipImageResource(position, org.sopt.mainfeature.R.drawable.ic_clip_all_24_primary, org.sopt.mainfeature.R.drawable.ic_clip_24_primary)
      org.sopt.mainfeature.R.color.primary
    } else {
      setClipImageResource(position, org.sopt.mainfeature.R.drawable.ic_home_clip_20, org.sopt.mainfeature.R.drawable.ic_clip_24)
      org.sopt.mainfeature.R.color.neutrals900
    }
    val color = ContextCompat.getColor(context, colorResource)
    binding.tvItemTimerClipCount.setTextColor(color)
    binding.tvItemTimerClipName.setTextColor(color)
  }

  private fun setClipImageResource(position: Int, imageResource1: Int, imageResource2: Int) {
    val imageResource = if (position == 0) imageResource1 else imageResource2
    binding.ivItemTimerClip.setImageResource(imageResource)
  }

  private fun setOnClickListener(data: Clip, onClick: (Clip, Int) -> Unit) {
    binding.root.setOnClickListener {
      onClick(data, bindingAdapterPosition)
    }
  }
}
