package org.sopt.mypage.search.viewholder

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.recyclerview.widget.RecyclerView
import org.sopt.mypage.databinding.ItemSearchResultClipBinding
import org.sopt.mypage.search.ClipResultDummy
import org.sopt.mypage.search.LinkResultDummy

class ClipResultViewHolder(val binding: ItemSearchResultClipBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun onBind(clipResult: ClipResultDummy, searchQuery: String) {
    val title = clipResult.title
    val spannable = SpannableString(title)

    binding.tvClipTitle.text = title
    binding.tvClipAmount.text = clipResult.amount.toString()


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

    binding.tvClipTitle.text = spannable

  }
}