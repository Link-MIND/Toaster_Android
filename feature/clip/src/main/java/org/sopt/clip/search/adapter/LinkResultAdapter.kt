package org.sopt.clip.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.databinding.ItemSearchResultClipLinkBinding
import org.sopt.clip.search.LinkResultDummy
import org.sopt.clip.search.viewholder.LinkResultViewHolder

class LinkResultAdapter :
  ListAdapter<LinkResultDummy, LinkResultViewHolder>(DiffUtilCallback) {

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

  private object DiffUtilCallback : DiffUtil.ItemCallback<LinkResultDummy>() {
    override fun areItemsTheSame(oldItem: LinkResultDummy, newItem: LinkResultDummy): Boolean {
      return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: LinkResultDummy, newItem: LinkResultDummy): Boolean {
      return oldItem == newItem
    }
  }
}
