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
  private lateinit var clipEditAdapter: ClipEditAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    initEditAdapter()
    updateEditListView()

    onClickBackButton()
  }

  private fun updateEditListView() {
    var state: Boolean = viewModel.mockClipData == null
    if (!state) {
      clipEditAdapter.submitList(viewModel.mockClipData)
    }
  }

  private fun initEditAdapter() {
    val clipEditAdapter = ClipEditAdapter()
    binding.rvClipEdit.adapter = clipEditAdapter
  }

  private fun onClickBackButton() {
    binding.ivClipEditBack.onThrottleClick {
      findNavController().navigateUp()
    }
  }
}
