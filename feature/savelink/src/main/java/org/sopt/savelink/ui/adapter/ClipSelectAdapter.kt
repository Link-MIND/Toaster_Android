package org.sopt.savelink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.savelink.databinding.ItemTimerClipSelectBinding
import org.sopt.savelink.ui.model.Clip
import org.sopt.savelink.ui.viewholder.ClipSelectViewHolder
import org.sopt.ui.view.ItemDiffCallback

class ClipSelectAdapter(
    private val onClickClip: (Clip, Int) -> Unit,
) : ListAdapter<Clip, ClipSelectViewHolder>(DiffUtil) {
  private var selectedPosition = -1
  override fun onBindViewHolder(holder: ClipSelectViewHolder, position: Int) {
    holder.onBind(getItem(position), position) { clip, position ->
      handleClipSelection(clip, position)
      onClickClip(clip, position)
    }
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipSelectViewHolder {
    return ClipSelectViewHolder(
      ItemTimerClipSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )
  }
  private fun handleClipSelection(clip: Clip, position: Int) {
    if (position == 0) {
      clip.isSelected = true
    } else {
      // 다른 아이템을 선택하면 첫 번째 아이템의 선택을 해제합니다.
      getItem(0).isSelected = false
      notifyItemChanged(0)

      if (selectedPosition != position) {
        unselectClip()
        clip.isSelected = true
        selectedPosition = position
      } else {
        clip.isSelected = !clip.isSelected
        if (!clip.isSelected) {
          selectedPosition = -1
          selectFirstItemIfNoneSelected()
        }
      }
    }
    onClickClip(clip, position)
    notifyItemChanged(position)
  }

  private fun selectFirstItemIfNoneSelected() {
    if (selectedPosition == -1) {
      getItem(0).isSelected = true
      notifyItemChanged(0)
      selectedPosition = 0
    }
  }

  private fun unselectClip() {
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
