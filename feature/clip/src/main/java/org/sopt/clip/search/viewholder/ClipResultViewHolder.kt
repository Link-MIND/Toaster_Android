package org.sopt.clip.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.databinding.ItemSearchResultClipBinding
import org.sopt.model.category.Category
import org.sopt.ui.view.onThrottleClick

class ClipResultViewHolder(val binding: ItemSearchResultClipBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun onBind(clipResult: Category, onClick: (Category) -> Unit) {
    binding.tvClipTitle.text = clipResult.categoryTitle
    binding.tvClipAmount.text = clipResult.toastNum.toString()
    binding.root.onThrottleClick {
      onClick(clipResult)
    }
  }
}
