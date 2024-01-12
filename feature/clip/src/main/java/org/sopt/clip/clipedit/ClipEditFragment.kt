package org.sopt.clip.clipedit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.clip.ClipViewModel
import org.sopt.clip.databinding.FragmentClipEditBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class ClipEditFragment : BindingFragment<FragmentClipEditBinding>({ FragmentClipEditBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()
  private lateinit var clipEditAdapter: ClipEditAdapter
  private val itemTouchHelper by lazy { ItemTouchHelper(ItemTouchCallback(clipEditAdapter))}

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    clipEditAdapter = ClipEditAdapter{ itemId, state ->
      val todo:String
      if(state=="delete")
        todo="삭제"
      else todo="수정"
      Toast.makeText(context, "$todo + itemId: $itemId", Toast.LENGTH_SHORT).show()
    }
    binding.rvClipEdit.adapter = clipEditAdapter

    updateEditListView()

    onClickBackButton()
  }

  private fun updateEditListView() {
    viewModel.mockDataListState.observe(
      viewLifecycleOwner,
    ) {
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
