package org.sopt.clip.search.viewholder

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.clip.databinding.ItemClipLinkBinding
import org.sopt.clip.databinding.ItemSearchLinkBinding
import org.sopt.clip.search.util.applyBoldStyle
import org.sopt.model.category.Toast
import org.sopt.ui.view.onThrottleClick

class LinkResultViewHolder(val binding: ItemSearchLinkBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun onBind(linkData: Toast?, searchQuery: String, onClick: (Toast) -> Unit) {
    if (linkData == null) return
    with(binding) {
      tvLinkTitle.text = applyBoldStyle(linkData.toastTitle, searchQuery)
      tvLinkUrl.text = linkData.linkUrl
      if (linkData.categoryTitle.isNullOrEmpty()) {
        tvLinkClipTitle.isGone = true
      } else {
        tvLinkClipTitle.isVisible = true
        tvLinkClipTitle.text = linkData.categoryTitle
      }
      ivLinkThumnail.load(linkData.thumbnailUrl) {
      }
      if (linkData.isRead!!) {
        tvItemClipLink.isVisible = true
      } else {
        tvItemClipLink.isGone = true
      }
    }
    binding.root.onThrottleClick {
      onClick(linkData)
    }
  }
}
