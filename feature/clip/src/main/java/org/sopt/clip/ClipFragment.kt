package org.sopt.clip

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.clip.databinding.FragmentClipBinding
import org.sopt.ui.base.BindingFragment

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
