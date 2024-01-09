package org.sopt.clip.clipdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.clip.ClipViewModel
import org.sopt.clip.databinding.FragmentClipDetailBinding
import org.sopt.ui.base.BindingFragment


class ClipDetailFragment : BindingFragment<FragmentClipDetailBinding>({ FragmentClipDetailBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val clipAdapter = ClipAdapter(requireContext())
    clipAdapter.setClipList(viewModel.mockClipData)
    binding.rvCategoryLink.adapter = clipAdapter
  }
}
