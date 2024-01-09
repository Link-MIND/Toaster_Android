package org.sopt.clip.clipdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.LinkDTO
import org.sopt.clip.databinding.ItemClipDetailLinkBinding
import org.sopt.ui.view.ItemDiffCallback

class ClipLinkAdapter : ListAdapter<LinkDTO, ClipLinkViewHolder>(DiffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipLinkViewHolder {
    return ClipLinkViewHolder(
      ItemClipDetailLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )
  }

  override fun onBindViewHolder(holder: ClipLinkViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<LinkDTO>(
      onItemsTheSame = { old, new -> old.url == new.url },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
