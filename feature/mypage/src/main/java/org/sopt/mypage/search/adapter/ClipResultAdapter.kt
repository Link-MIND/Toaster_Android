package org.sopt.mypage.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.sopt.mypage.databinding.ItemSearchResultClipBinding
import org.sopt.mypage.search.ClipResultDummy
import org.sopt.mypage.search.viewholder.ClipResultViewHolder

class ClipResultAdapter :
  ListAdapter<ClipResultDummy, ClipResultViewHolder>(DiffUtilCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipResultViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = ItemSearchResultClipBinding.inflate(inflater, parent, false)
    return ClipResultViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ClipResultViewHolder, position: Int) {
    val clipResult = getItem(position)
    holder.bind(clipResult)
  }

  private object DiffUtilCallback : DiffUtil.ItemCallback<ClipResultDummy>() {
    override fun areItemsTheSame(oldItem: ClipResultDummy, newItem: ClipResultDummy): Boolean {
      return oldItem.amount == newItem.amount
    }

    override fun areContentsTheSame(oldItem: ClipResultDummy, newItem: ClipResultDummy): Boolean {
      return oldItem == newItem
    }
  }
}
