package org.sopt.clip.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.databinding.ItemSearchResultClipBinding
import org.sopt.clip.search.viewholder.ClipResultViewHolder
import org.sopt.model.category.Category
import org.sopt.ui.view.ItemDiffCallback

class ClipResultAdapter :
  ListAdapter<Category, ClipResultViewHolder>(DiffUtil) {

  private var searchQuery: String = ""

  fun setSearchQuery(query: String) {
    searchQuery = query
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipResultViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = ItemSearchResultClipBinding.inflate(inflater, parent, false)
    return ClipResultViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ClipResultViewHolder, position: Int) {
    val result = getItem(position)
    holder.onBind(result)
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<Category>(
      onItemsTheSame = { old, new -> old.categoryId == new.categoryId },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
