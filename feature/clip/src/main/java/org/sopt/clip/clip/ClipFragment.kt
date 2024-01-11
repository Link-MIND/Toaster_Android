package org.sopt.clip.clip

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import org.sopt.clip.ClipViewModel
import org.sopt.clip.databinding.FragmentClipBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class ClipFragment : BindingFragment<FragmentClipBinding>({ FragmentClipBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val clipAdapter = ClipAdapter { clipId ->
      Toast.makeText(context, "클릭된 item id: $clipId", Toast.LENGTH_SHORT).show()
    }
    binding.rvClipClip.adapter = clipAdapter
    if (viewModel.mockClipData == null) {
      clipAdapter.submitList((viewModel.mockClipData))
    } else {
      binding.ivClipEmpty.visibility = View.GONE
      binding.tvClipEmpty.visibility = View.GONE
      clipAdapter.submitList(viewModel.mockClipData)
    }
    binding.clClipSearch.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_detail)
    }
  }
}
