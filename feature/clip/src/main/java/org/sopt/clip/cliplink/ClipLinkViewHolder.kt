package org.sopt.clip.cliplink

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.clip.databinding.ItemClipLinkBinding
import org.sopt.model.category.CategoryLink
import org.sopt.ui.view.onThrottleClick

class ClipLinkViewHolder(
  private val binding: ItemClipLinkBinding,
  private val onClickItemLink: (CategoryLink, String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(linkData: CategoryLink, onClick: (CategoryLink, String) -> Unit) {
    if (linkData == null) return
    with(binding) {
      tvLinkTitle.text = linkData.toastTitle
      tvLinkUrl.text = linkData.linkUrl
      if (linkData.categoryTitle.isNullOrEmpty()) {
        tvLinkClipTitle.isGone = true
      } else {
        tvLinkClipTitle.isVisible = true
        tvLinkClipTitle.text = linkData.categoryTitle
      }
      ivLinkThumnail.load(linkData.thumbnailUrl) {
      }
      if (linkData.isRead) {
        tvItemClipLink.isVisible = true
      } else {
        tvItemClipLink.isGone = true
      }
      root.onThrottleClick {
        /*onClick(linkData)*/
        onClickItemLink(linkData, "click")
        /*
                initLinkClipTitleVisible(linkData)
        */
      }
      ivLinkDelete.onThrottleClick {
        onClickItemLink(linkData, "delete")
      }
    }
  }
}
