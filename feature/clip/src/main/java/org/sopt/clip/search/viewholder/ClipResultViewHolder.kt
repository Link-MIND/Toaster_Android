package org.sopt.clip.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.databinding.ItemSearchResultClipBinding
import org.sopt.clip.search.ClipResultDummy
import org.sopt.clip.search.util.applyBoldStyle

class ClipResultViewHolder(val binding: ItemSearchResultClipBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun onBind(clipResult: ClipResultDummy, searchQuery: String) {
    binding.tvClipTitle.text = applyBoldStyle(clipResult.title, searchQuery)
    binding.tvClipAmount.text = clipResult.amount.toString()
  }
}
