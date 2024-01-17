package org.sopt.clip.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.databinding.ItemSearchResultClipLinkBinding
import org.sopt.clip.search.LinkResult
import org.sopt.clip.search.viewholder.LinkResultViewHolder
import org.sopt.model.category.Category
import org.sopt.model.category.Toast
import org.sopt.ui.view.ItemDiffCallback

class LinkResultAdapter :
  ListAdapter<Toast, LinkResultViewHolder>(DiffUtil) {

  private var searchQuery: String = ""

  fun setSearchQuery(query: String) {
    searchQuery = query
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkResultViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = ItemSearchResultClipLinkBinding.inflate(inflater, parent, false)
    return LinkResultViewHolder(binding)
  }

  override fun onBindViewHolder(holder: LinkResultViewHolder, position: Int) {
    val result = getItem(position)
    holder.onBind(result, searchQuery)
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<Toast>(
      onItemsTheSame = { old, new -> old.toastId == new.toastId },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
