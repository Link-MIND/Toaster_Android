package org.sopt.clip.cliplink

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.LinkDTO
import org.sopt.clip.databinding.ItemClipLinkBinding
import org.sopt.ui.view.ItemDiffCallback

class ClipLinkAdapter(
  val onClick: (LinkDTO) -> Unit,
  private val onClickItemLink: (Long, String) -> Unit,
) : ListAdapter<LinkDTO, ClipLinkViewHolder>(DiffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipLinkViewHolder {
    return ClipLinkViewHolder(
      ItemClipLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onClickItemLink,
    )
  }

  override fun onBindViewHolder(holder: ClipLinkViewHolder, position: Int) {
    val linkDTO = getItem(position)
    holder.onBind(linkDTO, onClick)
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<LinkDTO>(
      onItemsTheSame = { old, new -> old.url == new.url },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
