package org.sopt.clip.search.viewholder

import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.clip.databinding.ItemSearchResultClipLinkBinding
import org.sopt.clip.search.util.applyBoldStyle
import org.sopt.model.category.Toast
import org.sopt.ui.view.onThrottleClick

class LinkResultViewHolder(val binding: ItemSearchResultClipLinkBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun onBind(linkResult: Toast, searchQuery: String, onClick: (Toast) -> Unit) {
    binding.tvClipDetailTitle.text = linkResult.categoryTitle
    if (linkResult.categoryTitle == null) {
      binding.tvClipDetailTitle.isGone = true
    }
    binding.tvClipTitle.text = applyBoldStyle(linkResult.toastTitle, searchQuery)
    binding.tvClipUrl.text = linkResult.linkUrl
    binding.ivLinkThumnail.load(linkResult.thumbnailUrl)
    binding.root.onThrottleClick {
      onClick(linkResult)
    }
  }
}
