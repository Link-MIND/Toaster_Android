package org.sopt.clip.clipdetail

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.LinkDTO
import org.sopt.clip.databinding.ItemClipDetailLinkBinding

class ClipLinkViewHolder(
  private val binding: ItemClipDetailLinkBinding,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(linkData: LinkDTO) {
    if (linkData != null) {
      with(binding) {
        tvLinkUrl.text = linkData.linkTitle
        tvLinkUrl.text = linkData.url
      }
    }
  }
}
