package org.sopt.mypage.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.mypage.databinding.ItemSearchResultClipLinkBinding
import org.sopt.mypage.search.LinkResultDummy

class LinkResultViewHolder(val binding: ItemSearchResultClipLinkBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun bind(linkResult: LinkResultDummy) {
    binding.tvClipDetailTitle.text = linkResult.detailcliptitle
    binding.tvClipTitle.text = linkResult.title
    binding.tvClipUrl.text = linkResult.url
  }
}
