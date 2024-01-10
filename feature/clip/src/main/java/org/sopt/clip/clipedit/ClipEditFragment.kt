package org.sopt.clip.clipedit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.sopt.clip.ClipViewModel
import org.sopt.clip.R
import org.sopt.clip.databinding.FragmentClipEditBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class ClipEditFragment : BindingFragment<FragmentClipEditBinding>({ FragmentClipEditBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val clipEditAdapter = ClipEditAdapter({})
    binding.rvClipEdit.adapter = clipEditAdapter
    var state: Boolean = viewModel.mockClipData == null

    if (!state) {
      clipEditAdapter.submitList(viewModel.mockClipData)
    }

    with(binding) {
      ivClipEditBack.setOnClickListener {
      }
    }

    binding.ivClipEditBack.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_clip_edit_to_navigation_clip)
    }
  }
}
