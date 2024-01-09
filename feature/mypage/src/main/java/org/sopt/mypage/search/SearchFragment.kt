package org.sopt.mypage.search

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.StyleSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.ConcatAdapter
import org.sopt.mypage.databinding.FragmentSearchBinding
import org.sopt.mypage.search.adapter.ClipResultAdapter
import org.sopt.mypage.search.adapter.LinkResultAdapter
import org.sopt.mypage.search.viewholder.ClipResultViewHolder
import org.sopt.mypage.search.viewholder.LinkResultViewHolder
import org.sopt.ui.base.BindingFragment

class SearchFragment : BindingFragment<FragmentSearchBinding>({ FragmentSearchBinding.inflate(it) }) {

  private lateinit var linkResultAdapter: LinkResultAdapter
  private lateinit var clipResultAdapter: ClipResultAdapter
  private lateinit var mResultAdapter: ConcatAdapter


  private val linkResults = listOf(
    LinkResultDummy("카테고리", "Title", "URL"),
    LinkResultDummy("카테고리2", "Title2", "URL2"),
  )
  private val clipResults = listOf(ClipResultDummy("토스터기", 6), ClipResultDummy("토스터", 8))

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    linkResultAdapter = LinkResultAdapter()
    clipResultAdapter = ClipResultAdapter()

    linkResultAdapter.submitList(linkResults)
    clipResultAdapter.submitList(clipResults)

    mResultAdapter = ConcatAdapter(linkResultAdapter, clipResultAdapter)
    binding.rcSearchResult.adapter = mResultAdapter

    binding.ivLeft.setOnClickListener {
      requireActivity().supportFragmentManager.popBackStack()
    }

    binding.ivCancel.setOnClickListener {
      binding.editText.text.clear()

      val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(binding.editText.windowToken, 0)
    }

    binding.ivSearch.setOnClickListener {
      val searchTerm = binding.editText.text.toString()
      performSearch(searchTerm)
    }

    binding.editText.addTextChangedListener(
      object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
          binding.ivCancel.visibility = if (charSequence.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
        }

        override fun afterTextChanged(editable: Editable?) {}
      },
    )

    binding.ivCancel.setOnClickListener {
      binding.editText.text.clear()
      hideSoftKeyboard()
    }
  }

  private fun performSearch(searchTerm: String) {

    val matchingResults = findMatchingResults(searchTerm)

    if (matchingResults.matchingTitles.isEmpty()) {

      binding.clNoneResults.visibility = View.VISIBLE
      binding.rcSearchResult.visibility = View.GONE
    } else {

      binding.clNoneResults.visibility = View.GONE
      binding.rcSearchResult.visibility = View.VISIBLE

      linkResultAdapter.submitList(matchingResults.linkResults)
      clipResultAdapter.submitList(matchingResults.clipResults)

      applyBoldStyling(matchingResults.matchingTitles, searchTerm)
    }
  }

  private fun findMatchingResults(searchTerm: String): MatchingResults {
    val matchingTitles = mutableListOf<String>()
    val matchingLinkResults = mutableListOf<LinkResultDummy>()
    val matchingClipResults = mutableListOf<ClipResultDummy>()


    for (linkResult in linkResults) {
      if (linkResult.title.contains(searchTerm, ignoreCase = true)) {
        matchingTitles.add(linkResult.title)
        matchingLinkResults.add(linkResult)
      }
    }

    for (clipResult in clipResults) {
      if (clipResult.title.contains(searchTerm, ignoreCase = true)) {
        matchingTitles.add(clipResult.title)
        matchingClipResults.add(clipResult)
      }
    }

    return MatchingResults(matchingLinkResults, matchingClipResults, matchingTitles)
  }

  private fun applyBoldStyling(matchingTitles: List<String>, searchTerm: String) {
    for (matchingTitle in matchingTitles) {
      val start = matchingTitle.indexOf(searchTerm, ignoreCase = true)
      val end = start + searchTerm.length

      if (start != -1) {
        val spannableString = SpannableString(matchingTitle)
        spannableString.setSpan(
          StyleSpan(Typeface.BOLD),
          start,
          end,
          Spannable.SPAN_INCLUSIVE_INCLUSIVE,
        )


        val adapterPosition = findAdapterPosition(matchingTitle)
        if (adapterPosition != -1) {
          val viewHolder =
            binding.rcSearchResult.findViewHolderForAdapterPosition(adapterPosition)
          if (viewHolder is LinkResultViewHolder) {
            viewHolder.binding.tvClipTitle.text = spannableString
          } else if (viewHolder is ClipResultViewHolder) {
            viewHolder.binding.tvClipTitle.text = spannableString
          }
        }
      }
    }
  }

  private fun findAdapterPosition(matchingTitle: String): Int {
    for ((index, item) in linkResultAdapter.currentList.withIndex()) {
      if (item is LinkResultDummy && item.title == matchingTitle) {
        return index
      }
    }
    for ((index, item) in clipResultAdapter.currentList.withIndex()) {
      if (item is ClipResultDummy && item.title == matchingTitle) {
        return index + linkResultAdapter.itemCount
      }
    }

    return -1
  }

  private fun hideSoftKeyboard() {
    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(binding.editText.windowToken, 0)
  }
}


