package org.sopt.mypage.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import org.sopt.mypage.databinding.FragmentSearchBinding
import org.sopt.mypage.search.adapter.ClipResultAdapter
import org.sopt.mypage.search.adapter.LinkResultAdapter
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick
import org.sopt.ui.context.hideKeyboard

class SearchFragment : BindingFragment<FragmentSearchBinding>(
  { FragmentSearchBinding.inflate(it) }
) {

  private val viewModel: SearchViewModel by viewModels()
  private lateinit var linkResultAdapter: LinkResultAdapter
  private lateinit var clipResultAdapter: ClipResultAdapter
  private lateinit var mResultAdapter: ConcatAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    linkResultAdapter = LinkResultAdapter()
    clipResultAdapter = ClipResultAdapter()
    mResultAdapter = ConcatAdapter(linkResultAdapter, clipResultAdapter)

    binding.rcSearchResult.adapter = mResultAdapter

    setupObservers()
    setupDummyData()
    setupClickListeners()
  }

  private fun setupObservers() {
    observeLinkResults()
    observeClipResults()
  }

  private fun setupDummyData() {
    val linkResults = listOf(
      LinkResultDummy("Category", "토스트기", "URL"),
      LinkResultDummy("Category2", "토스트", "URL2")
    )
    val clipResults = listOf(
      ClipResultDummy("토스트 맛집", 6),
      ClipResultDummy("토스트굿", 8)
    )

    viewModel.updateResults(linkResults, clipResults)
  }

  private fun setupClickListeners() {
    binding.ivSearch.onThrottleClick {
      val query = binding.editText.text.toString().trim()

      if (query.isNotEmpty()) {
        val isMatchedResults = viewModel.onClickSearch(query)

        if (isMatchedResults) {
          binding.rcSearchResult.isVisible = true
          binding.clNoneResults.isVisible = false
          binding.ivSearch.isVisible = false
          binding.ivCancel.isVisible = true
        } else {
          binding.rcSearchResult.isVisible = false
          binding.clNoneResults.isVisible = true
          binding.ivSearch.isVisible = false
          binding.ivCancel.isVisible = true
        }
      } else {
        binding.rcSearchResult.isVisible = false
        binding.clNoneResults.isVisible = true
      }
      updateSearchQuery(query)
      requireContext().hideKeyboard(requireView())
    }

      binding.ivCancel.onThrottleClick {
        clearSearch()
        binding.rcSearchResult.isVisible = false
        requireContext().hideKeyboard(requireView())

        binding.clSearch.isVisible = true
        binding.ivCancel.isVisible = false
      }


    binding.ivLeft.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun observeLinkResults() {
    viewModel.linkResultsLiveData.observe(viewLifecycleOwner, Observer { linkResults ->
      linkResultAdapter.submitList(linkResults)
    })
  }

  private fun observeClipResults() {
    viewModel.clipResultsLiveData.observe(viewLifecycleOwner, Observer { clipResults ->
      clipResultAdapter.submitList(clipResults)
    })
  }

  private fun updateSearchQuery(query: String) {
    linkResultAdapter.setSearchQuery(query)
    clipResultAdapter.setSearchQuery(query)
  }

  private fun showNoneResults(show: Boolean) {
    binding.clNoneResults.visibility = if (show) View.VISIBLE else View.GONE
    binding.rcSearchResult.visibility = if (show) View.GONE else View.VISIBLE
  }

  private fun clearSearch() {
    binding.editText.text.clear()
  }
}
