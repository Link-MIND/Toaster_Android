package org.sopt.savelink.ui.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.sopt.savelink.databinding.ItemTimerClipSelectBinding
import org.sopt.savelink.ui.Clip
import org.sopt.ui.view.onThrottleClick

class ClipSelectViewHolder(
  val binding: ItemTimerClipSelectBinding,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: Clip?, position: Int, onClickClip: (Clip, Int) -> Unit) {
    if (data == null) return
    setImage(position)
    setTexts(data)
    setTextColor(data, position)
    setOnClickListener(data, onClickClip)
  }
  private fun setImage(position: Int) {
    val imageResource = if (position == 0) {
      org.sopt.mainfeature.R.drawable.ic_clip_all_24
    } else {
      org.sopt.mainfeature.R.drawable.ic_clip_24
    }
    binding.ivItemTimerClip.setImageResource(imageResource)
  }

  private fun setTexts(data: Clip) {
    with(binding) {
      tvItemTimerClipName.text = data.categoryTitle
      tvItemTimerClipCount.text = "${data.toastNum}개"
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
    val color = ContextCompat.getColor(binding.root.context, colorResource)
    binding.tvItemTimerClipCount.setTextColor(color)
    binding.tvItemTimerClipName.setTextColor(color)
  }

  private fun setClipImageResource(position: Int, selectedImage: Int, unselectedImage: Int) {
    val imageResource = if (position == 0) selectedImage else unselectedImage
    binding.ivItemTimerClip.setImageResource(imageResource)
  }

  private fun setOnClickListener(data: Clip, onClick: (Clip, Int) -> Unit) {
    binding.root.onThrottleClick {
      onClick(data, bindingAdapterPosition)
    }
  }
}
