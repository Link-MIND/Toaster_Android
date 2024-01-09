package org.sopt.mypage.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.mypage.databinding.FragmentSearchBinding
import org.sopt.mypage.search.adapter.ClipResultAdapter
import org.sopt.mypage.search.adapter.LinkResultAdapter
import org.sopt.ui.base.BindingFragment

class SearchFragment : BindingFragment<FragmentSearchBinding>({ FragmentSearchBinding.inflate(it) }) {

  private lateinit var linkResultAdapter: LinkResultAdapter
  private lateinit var clipResultAdapter: ClipResultAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.rcSearchResult.layoutManager = LinearLayoutManager(context)

    val linkResults = listOf(
      LinkResultDummy("Detail", "Title", "URL"),
      LinkResultDummy("fdfd", "eiei", "dsd"),
    )
    val clipResults = listOf(ClipResultDummy("Amount"), ClipResultDummy("sdsd"))

    linkResultAdapter = LinkResultAdapter()
    clipResultAdapter = ClipResultAdapter()

    linkResultAdapter.submitList(linkResults)
    clipResultAdapter.submitList(clipResults)

    binding.rcSearchResult.adapter = linkResultAdapter
  }
}
