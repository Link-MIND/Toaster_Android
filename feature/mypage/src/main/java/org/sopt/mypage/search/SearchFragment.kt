package org.sopt.mypage.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.mypage.databinding.FragmentSearchBinding
import org.sopt.mypage.search.adapter.ClipResultAdapter
import org.sopt.mypage.search.adapter.LinkResultAdapter
import org.sopt.mypage.common.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

  private lateinit var linkResultAdapter: LinkResultAdapter
  private lateinit var clipResultAdapter: ClipResultAdapter

  override fun inflateBinding(
    inflater: LayoutInflater,
    container: ViewGroup?,
    attachToRoot: Boolean
  ): FragmentSearchBinding {
    return FragmentSearchBinding.inflate(inflater, container, attachToRoot)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.rcSearchResult.layoutManager = LinearLayoutManager(context)

    val linkResults = listOf(
      LinkResultDummy("Detail", "Title", "URL"),
      LinkResultDummy("fdfd", "eiei", "dsd")
    )
    val clipResults = listOf(ClipResultDummy("Amount"), ClipResultDummy("sdsd"))

    linkResultAdapter = LinkResultAdapter()
    clipResultAdapter = ClipResultAdapter()

    linkResultAdapter.submitList(linkResults)
    clipResultAdapter.submitList(clipResults)

    binding.rcSearchResult.adapter = linkResultAdapter
  }
}
