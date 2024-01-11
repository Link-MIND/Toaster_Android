package org.sopt.mypage.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.mypage.databinding.ItemSearchResultClipLinkBinding
import org.sopt.mypage.search.LinkResultDummy
import org.sopt.mypage.search.util.applyBoldStyle

class LinkResultViewHolder(val binding: ItemSearchResultClipLinkBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun onBind(linkResult: LinkResultDummy, searchQuery: String) {
    binding.tvClipDetailTitle.text = linkResult.detailcliptitle
    binding.tvClipTitle.text = applyBoldStyle(linkResult.title, searchQuery)
    binding.tvClipUrl.text = linkResult.url
  }
}
