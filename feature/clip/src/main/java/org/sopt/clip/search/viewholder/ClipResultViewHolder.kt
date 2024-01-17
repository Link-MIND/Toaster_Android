package org.sopt.clip.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.databinding.ItemSearchResultClipBinding
import org.sopt.model.category.Category

class ClipResultViewHolder(val binding: ItemSearchResultClipBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun onBind(clipResult: Category/*, searchQuery: String*/) {
    binding.tvClipTitle.text = clipResult.categoryTitle
      //applyBoldStyle(clipResult.categoryTitle, searchQuery)
    binding.tvClipAmount.text = clipResult.toastNum.toString()
  }
}
