package org.sopt.clip.clip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.databinding.ItemClipClipBinding
import org.sopt.model.category.Category
import org.sopt.ui.view.ItemDiffCallback

class ClipAdapter(
  private val onClickItemClip: (Long) -> Unit,
) : ListAdapter<Category, ClipViewHolder>(DiffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipViewHolder {
    return ClipViewHolder(
      ItemClipClipBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onClickItemClip,
    )
  }

  override fun onBindViewHolder(holder: ClipViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<Category>(
      onItemsTheSame = { old, new -> old.categoryId == new.categoryId },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
