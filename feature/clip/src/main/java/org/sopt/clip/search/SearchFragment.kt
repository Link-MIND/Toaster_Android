package org.sopt.clip.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.clip.databinding.FragmentSearchBinding
import org.sopt.clip.search.adapter.ClipResultAdapter
import org.sopt.clip.search.adapter.LinkResultAdapter
import org.sopt.common.util.delSpace
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.context.hideKeyboard
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope
import org.sopt.ui.nav.DeepLinkUtil
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentSearchBinding>({ FragmentSearchBinding.inflate(it) }) {
  private val viewModel: SearchViewModel by viewModels()
  private lateinit var linkResultAdapter: LinkResultAdapter
  private lateinit var clipResultAdapter: ClipResultAdapter
  private lateinit var mResultAdapter: ConcatAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    linkResultAdapter = LinkResultAdapter { naviagateToWebViewFragment(it.linkUrl!!, it.toastId, it.isRead!!) }
    clipResultAdapter = ClipResultAdapter {
      navigateToDestination(
        "featureSaveLink://ClipLinkFragment?categoryId=${it.categoryId}",
      )
    }
    mResultAdapter = ConcatAdapter(linkResultAdapter, clipResultAdapter)

    binding.rcSearchResult.adapter = mResultAdapter
    setOnClickListeners()
    handleEditText()
    viewModel.searchState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is SearchState.Success -> {
          Log.e("카테고리 리스트", state.data.categories.toString())
          clipResultAdapter.submitList(state.data.categories)
          linkResultAdapter.submitList(state.data.toasts)
          handleSearchResultsVisibility()
        }

        is SearchState.Empty -> {}
        is SearchState.Failure -> {
          handleEmptyResults()
        }

        is SearchState.NoResult -> {
          handleEmptyResults()
        }

        else -> {}
      }
    }.launchIn(viewLifeCycleScope)
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

    binding.editText.setOnEditorActionListener { textView, i, keyEvent ->
      handleSearch()
      true
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
      viewModel.getSearchResult(query)
      handleSearchResultsVisibility()
    } else {
      handleEmptyQuery()
    }
    updateSearchQuery(query)
    requireContext().hideKeyboard(requireView())
  }

  private fun handleSearchResultsVisibility() {
    with(binding) {
      rcSearchResult.isVisible = true
      clNoneResults.isGone = true
      ivSearch.isVisible = false
      ivCancel.isVisible = true
    }
  }

  private fun handleEmptyResults() {
    binding.rcSearchResult.isVisible = false
    binding.clNoneResults.isVisible = true
  }

  private fun handleEmptyQuery() {
    binding.rcSearchResult.isVisible = false
    binding.clNoneResults.isVisible = false
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

  private fun updateSearchQuery(query: String) {
    linkResultAdapter.setSearchQuery(query)
    clipResultAdapter.setSearchQuery(query)
  }

  private fun clearSearch() {
    binding.editText.text.clear()
  }

  private fun naviagateToWebViewFragment(site: String, toastId: Long, isRead: Boolean) {
    navigateToDestination("featureSaveLink://webViewFragment?site=$site,,,$toastId,,,$isRead")
  }

  private fun navigateToDestination(destination: String) {
    val (request, navOptions) = DeepLinkUtil.getNavRequestNotPopUpAndOption(
      destination.delSpace(),
      enterAnim = org.sopt.mainfeature.R.anim.from_bottom,
      exitAnim = android.R.anim.fade_out,
      popEnterAnim = android.R.anim.fade_in,
      popExitAnim = org.sopt.mainfeature.R.anim.to_bottom,
    )
    findNavController().navigate(request, navOptions)
  }
}
