package org.sopt.clip.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.databinding.ItemSearchResultClipLinkBinding
import org.sopt.clip.search.LinkResultDummy
import org.sopt.clip.search.util.applyBoldStyle

class LinkResultViewHolder(val binding: ItemSearchResultClipLinkBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun onBind(linkResult: LinkResultDummy, searchQuery: String) {
    binding.tvClipDetailTitle.text = linkResult.detailcliptitle
    binding.tvClipTitle.text = applyBoldStyle(linkResult.title, searchQuery)
    binding.tvClipUrl.text = linkResult.url
  }
}
