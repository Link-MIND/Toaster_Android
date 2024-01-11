package org.sopt.clip.clipdetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.LinkDTO
import org.sopt.clip.databinding.ItemClipDetailLinkBinding

class ClipLinkViewHolder(
  private val binding: ItemClipDetailLinkBinding,
  private val onClickItemLink:(Long)-> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(linkData: LinkDTO) {
    if (linkData != null) {
      with(binding) {
        tvLinkTitle.text = linkData.linkTitle
        tvLinkUrl.text = linkData.url
        initLinkClipTitleVisible(linkData)
        root.setOnClickListener {
          onClickItemLink(linkData.linkId)
        }
      }
    }
  }
}
