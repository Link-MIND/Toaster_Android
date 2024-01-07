package org.sopt.mypage.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.mypage.databinding.FragmentSearchBinding
import org.sopt.mypage.search.adapter.ClipResultAdapter
import org.sopt.mypage.search.adapter.LinkResultAdapter

class SearchFragment : Fragment() {

  private lateinit var linkResultAdapter: LinkResultAdapter
  private lateinit var clipResultAdapter: ClipResultAdapter
  private var _binding: FragmentSearchBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    _binding = FragmentSearchBinding.inflate(inflater, container, false)
    val view = binding.root

    binding.rcSearchResult.layoutManager = LinearLayoutManager(context)

    val linkResults = listOf(
      LinkResultDummy("Detail", "Title", "URL"),
      (LinkResultDummy("fdfd", "eiei", "dsd")),
    )
    val clipResults = listOf(ClipResultDummy("Amount"), ClipResultDummy("sdsd"))

    linkResultAdapter.submitList(linkResults)
    clipResultAdapter.submitList(clipResults)

    binding.rcSearchResult.adapter = linkResultAdapter
    binding.rcSearchResult.adapter = clipResultAdapter
    return view
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
