package org.sopt.mypage.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.mypage.databinding.ItemSearchResultClipBinding
import org.sopt.mypage.search.ClipResultDummy

class ClipResultViewHolder(val binding: ItemSearchResultClipBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun bind(clipResult: ClipResultDummy) {
    binding.tvClipTitle.text = clipResult.title
    binding.tvClipAmount.text = clipResult.amount.toString()
  }
}
