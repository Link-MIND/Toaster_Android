package org.sopt.clip.clipedit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.bottomsheet.LinkMindBottomSheet
import designsystem.components.toast.linkMindSnackBar
import org.sopt.clip.ClipViewModel
import org.sopt.clip.databinding.FragmentClipEditBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class ClipEditFragment : BindingFragment<FragmentClipEditBinding>({ FragmentClipEditBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()
  private lateinit var clipEditAdapter: ClipEditAdapter
  private val itemTouchHelper by lazy { ItemTouchHelper(ItemTouchCallback(clipEditAdapter)) }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    clipEditAdapter = ClipEditAdapter { itemId, state ->
      when (state) {
        "delete" -> {}

        "edit" -> {
          showHomeBottomSheet()
        }
      }
      Toast.makeText(context, "$state + itemId: $itemId", Toast.LENGTH_SHORT).show()
    }
    binding.rvClipEdit.adapter = clipEditAdapter
    itemTouchHelper.attachToRecyclerView(binding.rvClipEdit)

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

  private fun showHomeBottomSheet() {
    val linkMindBottomSheet = LinkMindBottomSheet(requireContext())
    linkMindBottomSheet.show()
    linkMindBottomSheet.apply {
      setBottomSheetHint(org.sopt.mainfeature.R.string.home_new_clip_info)
      setTitle(org.sopt.mainfeature.R.string.edit_clip_edit_title)
      setErroMsg(org.sopt.mainfeature.R.string.error_clip_length)
      bottomSheetConfirmBtnClick {
        dismiss()
        requireContext().linkMindSnackBar(binding.root, "클립 수정 완료!", false)
      }
    }
  }

}
