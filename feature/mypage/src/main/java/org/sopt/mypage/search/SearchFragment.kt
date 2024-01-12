package org.sopt.mypage.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import org.sopt.mypage.databinding.FragmentSearchBinding
import org.sopt.mypage.search.adapter.ClipResultAdapter
import org.sopt.mypage.search.adapter.LinkResultAdapter
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.context.hideKeyboard
import org.sopt.ui.view.onThrottleClick

class SearchFragment : BindingFragment<FragmentSearchBinding>(
  { FragmentSearchBinding.inflate(it) },
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
    setOnClickListeners()
    handleEditText()
  }

  private fun setupObservers() {
    observeLinkResults()
    observeClipResults()
  }

  private fun setupDummyData() {
    val linkResults = listOf(
      LinkResultDummy("Category", "토스트기", "URL"),
      LinkResultDummy("Category2", "토스트", "URL2"),
    )
    val clipResults = listOf(
      ClipResultDummy("토스트 맛집", 6),
      ClipResultDummy("토스트굿", 8),
    )

    viewModel.updateResults(linkResults, clipResults)
  }

  private fun handleEditText() {
    binding.editText.doAfterTextChanged {
      handleEditTextChanges()
    }
  }

  private fun handleEditTextChanges() {
    binding.ivSearch.isVisible = true
    binding.clNoneResults.isGone = true
    binding.ivCancel.isGone = true
  }

  private fun setOnClickListeners() {
    binding.ivSearch.onThrottleClick {
      handleSearch()
    }

    binding.ivCancel.onThrottleClick {
      handleCancel()
    }

    binding.ivLeft.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun handleSearch() {
    val query = binding.editText.text.toString().trim()

    if (query.isNotEmpty()) {
      val isMatchedResults = viewModel.onClickSearch(query)
      handleSearchResultsVisibility(isMatchedResults)
    } else {
      handleEmptyResults()
    }
    updateSearchQuery(query)
    requireContext().hideKeyboard(requireView())
  }

  private fun handleSearchResultsVisibility(isMatchedResults: Boolean) {
    with(binding) {
      rcSearchResult.isVisible = isMatchedResults
      clNoneResults.isVisible = !isMatchedResults
      ivSearch.isVisible = false
      ivCancel.isVisible = true
    }
  }

  private fun handleEmptyResults() {
    binding.rcSearchResult.isVisible = false
    binding.clNoneResults.isVisible = true
  }

  private fun handleCancel() {
    with(binding) {
      rcSearchResult.isVisible = false
      clSearch.isVisible = true
      ivCancel.isVisible = false
      clNoneResults.isGone = true
    }
    requireContext().hideKeyboard(requireView())
    clearSearch()
  }

  private fun observeLinkResults() {
    viewModel.linkResultsLiveData.observe(
      viewLifecycleOwner,
      Observer { linkResults ->
        linkResultAdapter.submitList(linkResults)
      },
    )
  }

  private fun observeClipResults() {
    viewModel.clipResultsLiveData.observe(
      viewLifecycleOwner,
      Observer { clipResults ->
        clipResultAdapter.submitList(clipResults)
      },
    )
  }

  private fun updateSearchQuery(query: String) {
    linkResultAdapter.setSearchQuery(query)
    clipResultAdapter.setSearchQuery(query)
  }

  private fun clearSearch() {
    binding.editText.text.clear()
  }
}
