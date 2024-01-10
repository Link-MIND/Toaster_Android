package org.sopt.mypage.search.viewholder

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.recyclerview.widget.RecyclerView
import org.sopt.mypage.databinding.ItemSearchResultClipLinkBinding
import org.sopt.mypage.search.LinkResultDummy

class LinkResultViewHolder(val binding: ItemSearchResultClipLinkBinding) :
  RecyclerView.ViewHolder(binding.root) {


  fun onBind(linkResult: LinkResultDummy, searchQuery: String) {
    val title = linkResult.title
    val spannable = SpannableString(title)

    binding.tvClipDetailTitle.text = linkResult.detailcliptitle
    binding.tvClipTitle.text = title
    binding.tvClipUrl.text = linkResult.url


    // 검색어를 굵게 하이라이팅
    val startIndex = title.indexOf(searchQuery)
    if (startIndex != -1) {
      val endIndex = startIndex + searchQuery.length
      spannable.setSpan(
        StyleSpan(Typeface.BOLD),
        startIndex,
        endIndex,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
      )
    }

    // TextView에 텍스트 설정
    binding.tvClipTitle.text = spannable

  }
}
