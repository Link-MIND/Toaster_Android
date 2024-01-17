package org.sopt.clip.cliplink

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.databinding.ItemClipLinkBinding
import org.sopt.model.category.CategoryLink
import org.sopt.ui.view.onThrottleClick

class ClipLinkViewHolder(
  private val binding: ItemClipLinkBinding,
  private val onClickItemLink: (CategoryLink, String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(linkData: CategoryLink, onClick: (CategoryLink, String) -> Unit) {
    if (linkData == null) {
      return
    } else {
      with(binding) {
        tvLinkTitle.text = linkData.toastTitle
        tvLinkUrl.text = linkData.linkUrl
        tvLinkClipTitle.text= linkData.categoryTitle
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
}
