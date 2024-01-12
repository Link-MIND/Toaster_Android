package org.sopt.clip.clipedit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.clip.ClipViewModel
import org.sopt.clip.databinding.FragmentClipEditBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class ClipEditFragment : BindingFragment<FragmentClipEditBinding>({ FragmentClipEditBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()
  private lateinit var clipEditAdapter: ClipEditAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    clipEditAdapter = ClipEditAdapter()
    binding.rvClipEdit.adapter = clipEditAdapter

    updateEditListView()

    onClickBackButton()
  }

  private fun updateEditListView() {
    viewModel.mockDataListState.observe(
      viewLifecycleOwner) {
        if (!it) {
          clipEditAdapter.submitList(viewModel.mockClipData)
        }
      }

    var state: Boolean = viewModel.mockClipData == null
    if (!state) {
      clipEditAdapter.submitList(viewModel.mockClipData)
    }
  }


  private fun onClickBackButton() {
    binding.ivClipEditBack.onThrottleClick {
      findNavController().navigateUp()
    }
  }
}
