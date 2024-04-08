package org.sopt.clip.cliplink

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.databinding.ItemClipLinkBinding
import org.sopt.model.category.CategoryLink
import org.sopt.ui.view.ItemDiffCallback

class ClipLinkAdapter(
  val onClick: (CategoryLink, String) -> Unit,
) : ListAdapter<CategoryLink, ClipLinkViewHolder>(DiffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipLinkViewHolder {
    return ClipLinkViewHolder(
      ItemClipLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onClick,
    )
  }

  override fun onBindViewHolder(holder: ClipLinkViewHolder, position: Int) {
    val linkDTO = getItem(position)
    holder.onBind(linkDTO, onClick)
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<CategoryLink>(
      onItemsTheSame = { old, new -> old.toastId == new.toastId },
      onContentsTheSame = { old, new -> old.toastTitle == new.toastTitle },
    )
  }
}
