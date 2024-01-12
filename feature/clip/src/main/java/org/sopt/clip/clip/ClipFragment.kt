package org.sopt.clip.clip

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.clip.ClipViewModel
import org.sopt.clip.R
import org.sopt.clip.databinding.FragmentClipBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick
@AndroidEntryPoint
class ClipFragment : BindingFragment<FragmentClipBinding>({ FragmentClipBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val clipAdapter = ClipAdapter({})
    binding.rvClipClip.adapter = clipAdapter
    if (viewModel.mockClipData == null) {
      clipAdapter.submitList(viewModel.mockClipData)
    } else {
      binding.ivClipEmpty.visibility = View.GONE
      binding.tvClipEmpty.visibility = View.GONE
      clipAdapter.submitList(viewModel.mockClipData)
    }
    onClickSearchButton()
    onClickListView()
    onClickEditButton()
  }

  private fun onClickEditButton() {
    binding.btnClipEdit.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_edit)
      }
  }

  private fun onClickListView() {
    binding.rvClipClip.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_detail)
      }
  }

  private fun onClickSearchButton() {
    binding.clClipSearch.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_detail)
    }
  }
}
