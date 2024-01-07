package org.sopt.savelink.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.savelink.R
import org.sopt.savelink.databinding.ItemTimerClipSelectBinding
import org.sopt.savelink.ui.Clip
import org.sopt.savelink.ui.viewholder.ClipSelectViewHolder
import org.sopt.ui.view.ItemDiffCallback

class ClipSelectAdapter(
  private val onClick: (Clip, Int) -> Unit,
  private val context: Context,
) : ListAdapter<Clip, ClipSelectViewHolder>(DiffUtil) {
  private var selectedPosition = -1
  override fun onBindViewHolder(holder: ClipSelectViewHolder, position: Int) {
//    if (position==0) holder.binding.ivItemTimerClip.setImageResource(org.sopt.mainfeature.R.drawable.ic_home_clip_20)
    holder.onBind(getItem(position),position)
    { clip, position ->
      if (selectedPosition != position) {
        if (selectedPosition != -1) {
          getItem(selectedPosition).isSelected = false
          notifyItemChanged(selectedPosition)
        }
        clip.isSelected = true
        selectedPosition = position
      } else {
        clip.isSelected = !clip.isSelected
        if (!clip.isSelected) {
          selectedPosition = -1
        }
      }
      notifyItemChanged(position)
      onClick(clip, position)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipSelectViewHolder {
    return ClipSelectViewHolder(
      ItemTimerClipSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      context,
    )
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<Clip>(
      onItemsTheSame = { old, new -> old == new },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
