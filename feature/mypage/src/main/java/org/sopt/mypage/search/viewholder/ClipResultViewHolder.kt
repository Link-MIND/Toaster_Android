package org.sopt.mypage.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.mypage.databinding.ItemSearchResultClipBinding
import org.sopt.mypage.search.ClipResultDummy
import org.sopt.mypage.search.util.applyBoldStyle

class ClipResultViewHolder(val binding: ItemSearchResultClipBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun onBind(clipResult: ClipResultDummy, searchQuery: String) {
    binding.tvClipTitle.text = applyBoldStyle(clipResult.title, searchQuery)
    binding.tvClipAmount.text = clipResult.amount.toString()
  }
}
