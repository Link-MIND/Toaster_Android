package org.sopt.savelink.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
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
    holder.onBind(getItem(position), position) { clip, position ->
      handleClipSelection(clip, position)
      onClick(clip, position)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipSelectViewHolder {
    return ClipSelectViewHolder(
      ItemTimerClipSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      context,
    )
  }
  private fun handleClipSelection(clip: Clip, position: Int) {
    if (selectedPosition != position) {
      deselectPreviousClip()
      clip.selectNewClip()
      selectedPosition = position
    } else {
      clip.toggleSelection()
      if (!clip.isSelected) {
        selectedPosition = -1
      }
    }
    notifyItemChanged(position)
  }

  private fun deselectPreviousClip() {
    if (selectedPosition != -1) {
      getItem(selectedPosition).isSelected = false
      notifyItemChanged(selectedPosition)
    }
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<Clip>(
      onItemsTheSame = { old, new -> old == new },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
