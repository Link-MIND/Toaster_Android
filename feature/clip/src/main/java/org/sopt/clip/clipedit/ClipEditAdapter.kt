package org.sopt.clip.clipedit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.ClipsDTO
import org.sopt.clip.databinding.ItemClipEditClipBinding
import org.sopt.ui.view.ItemDiffCallback

class ClipEditAdapter(
  private val onClickItemClip: (ClipsDTO) -> Unit,
) : ListAdapter<ClipsDTO, ClipEditViewHolder>(DiffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipEditViewHolder {
    return ClipEditViewHolder(
      ItemClipEditClipBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onClickItemClip,
    )
  }

  override fun onBindViewHolder(holder: ClipEditViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<ClipsDTO>(
      onItemsTheSame = { old, new -> old.clipName == new.clipName },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
