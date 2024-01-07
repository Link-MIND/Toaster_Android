package org.sopt.timer.settimer.clipselect

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.timer.databinding.ItemTimerClipSelectBinding
import org.sopt.timer.dummymodel.Clip
import org.sopt.ui.view.ItemDiffCallback

class ClipSelectAdapter(
  private val onClick: (Clip, Int) -> Unit,
  private val context: Context,
) : ListAdapter<Clip, ClipSelectViewHolder>(DiffUtil) {
  private var selectedPosition = -1
  override fun onBindViewHolder(holder: ClipSelectViewHolder, position: Int) {
    holder.onBind(getItem(position), selectedPosition) { clip, position ->
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
