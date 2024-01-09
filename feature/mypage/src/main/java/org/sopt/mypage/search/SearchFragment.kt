package org.sopt.mypage.search

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import org.sopt.mypage.databinding.FragmentSearchBinding
import org.sopt.mypage.search.adapter.ClipResultAdapter
import org.sopt.mypage.search.adapter.LinkResultAdapter
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class SearchFragment : BindingFragment<FragmentSearchBinding>({ FragmentSearchBinding.inflate(it) }) {

  private val viewModel: SearchViewModel by viewModels()
  private lateinit var linkResultAdapter: LinkResultAdapter
  private lateinit var clipResultAdapter: ClipResultAdapter
  private lateinit var mResultAdapter: ConcatAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    linkResultAdapter = LinkResultAdapter()
    clipResultAdapter = ClipResultAdapter()

    viewModel.linkResults.observe(
      viewLifecycleOwner,
      Observer {
        linkResultAdapter.submitList(it)
      },
    )

    viewModel.clipResults.observe(
      viewLifecycleOwner,
      Observer {
        clipResultAdapter.submitList(it)
      },
    )

    viewModel.matchingTitles.observe(
      viewLifecycleOwner,
      Observer {
        handleMatchingTitlesUpdate(it)
      },
    )

    mResultAdapter = ConcatAdapter(linkResultAdapter, clipResultAdapter)
    binding.rcSearchResult.adapter = mResultAdapter

    binding.ivLeft.onThrottleClick {
      requireActivity().supportFragmentManager.popBackStack()
    }

    binding.ivCancel.onThrottleClick {
      binding.editText.text.clear()
    }

    binding.ivSearch.onThrottleClick {
      val searchTerm = binding.editText.text.toString()
      viewModel.searchResults(searchTerm)
    }

    binding.editText.doAfterTextChanged { text ->
      val isTextEmpty = text.isNullOrEmpty()
      binding.ivCancel.visibility = if (isTextEmpty) View.INVISIBLE else View.VISIBLE
    }

  }

  private fun handleMatchingTitlesUpdate(matchingTitles: List<String>) {
    if (matchingTitles.isEmpty()) {
      binding.clNoneResults.visibility = View.VISIBLE
      binding.rcSearchResult.visibility = View.GONE
    } else {
      binding.clNoneResults.visibility = View.GONE
      binding.rcSearchResult.visibility = View.VISIBLE
      mResultAdapter.notifyDataSetChanged()
      applyTextToBold(matchingTitles, viewModel.searchTerm)
    }
  }

  private fun applyTextToBold(matchingTitles: List<String>, searchTerm: String) {
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
      }
    }
  }
}

