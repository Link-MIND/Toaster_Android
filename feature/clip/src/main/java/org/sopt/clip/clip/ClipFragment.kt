package org.sopt.clip.clip

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.sopt.clip.ClipViewModel
import org.sopt.clip.R
import org.sopt.clip.databinding.FragmentClipBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class ClipFragment : BindingFragment<FragmentClipBinding>({ FragmentClipBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val clipAdapter = ClipAdapter(
      {},
    )
    binding.rvClipClip.adapter = clipAdapter

    clipAdapter.submitList(viewModel.mockClipData)

  }
}
