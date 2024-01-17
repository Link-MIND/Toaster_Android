package org.sopt.clip.clipedit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.bottomsheet.LinkMindBottomSheet
import designsystem.components.dialog.LinkMindDialog
import designsystem.components.toast.linkMindSnackBar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.clip.ClipViewModel
import org.sopt.clip.databinding.FragmentClipEditBinding
import org.sopt.mainfeature.R
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope
import org.sopt.ui.view.UiState
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class ClipEditFragment : BindingFragment<FragmentClipEditBinding>({ FragmentClipEditBinding.inflate(it) }) {
  private val viewModel by activityViewModels<ClipViewModel>()
  private lateinit var clipEditAdapter: ClipEditAdapter
  private val itemTouchHelper by lazy {
    ItemTouchHelper(ItemTouchCallback(clipEditAdapter))
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    clipEditAdapter = ClipEditAdapter(
      { itemId, state, position ->
        when (state) {
          "delete" -> {
            showDeleteDialog(itemId)
          }
          "edit" -> {
            showHomeBottomSheet(itemId)
          }
        }
        Toast.makeText(context, "$state + itemId: $itemId", Toast.LENGTH_SHORT).show()
      },
      deleteClip = {
        viewModel.deleteCategory(it)
      },
      onLongClick = {
        viewModel.update(it)
        viewModel.last2.flowWithLifecycle(viewLifeCycle).onEach { state ->
          when (state) {
            is UiState.Success -> {
              viewModel.patchCategoryEditPriority(it,state.data)
            }

            else -> {}
          }
        }.launchIn(viewLifeCycleScope)
      },
      onLongClick2 = {
        viewModel.update2(it.toInt())
      }
    )
    binding.rvClipEdit.adapter = clipEditAdapter
    itemTouchHelper.attachToRecyclerView(binding.rvClipEdit)
    clipEditAdapter.submitList((viewModel.categoryState.value as UiState.Success).data ?: emptyList())

    updateEditListView()
    onClickBackButton()
  }

  private fun updateEditListView() {
    viewModel.categoryState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          clipEditAdapter.submitList(state.data)
        }

        else -> {}
      }
    }.launchIn(viewLifeCycleScope)
  }

  private fun onClickBackButton() {
    viewModel.categoryDeleteState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          Log.d("test", "testsak")
          viewModel.getCategoryAll()
        }

        else -> {}
      }
    }
    binding.ivClipEditBack.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun showHomeBottomSheet(itemId: Long) {
    val editTitleBottomSheet = LinkMindBottomSheet(requireContext())
    editTitleBottomSheet.show()
    editTitleBottomSheet.apply {
      setBottomSheetHint(org.sopt.mainfeature.R.string.home_new_clip_info)
      setTitle(org.sopt.mainfeature.R.string.edit_clip_edit_title)
      /*if((viewModel.duplicateState.value is UiState.Success)){
        if((viewModel.duplicateState.value as UiState.Success).data.isDuplicate)
          setErroMsg(org.sopt.mainfeature.R.string.error_clip_name)
        }else {
        setErroMsg(org.sopt.mainfeature.R.string.error_clip_length)
      }*/
      bottomSheetConfirmBtnClick { // dto 수정됨
        val clipNewName = getText()
        Log.d("사용자가 입력한 클립명", "$clipNewName") // string 값 잘 가져옴
        viewModel.patchCategoryEditTitle(itemId, clipNewName)
        Log.d("사용자가 입력한 클립명2", "$clipNewName") // string 값 잘 가져옴

        editCategoryTitle()

        dismiss()
        requireContext().linkMindSnackBar(binding.root, "클립 수정 완료!", false)
      }
    }
  }

  fun editCategoryTitle() {
    viewModel.editTitleState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          Log.d("test", "$state")
          viewModel.getCategoryAll()
        }

        else -> {
          Log.d("test", "$state")
        }
      }
    }.launchIn(viewLifeCycleScope)
  }

  private fun showDeleteDialog(itemId: Long) {
    val deleteDialog = LinkMindDialog(requireContext())
    deleteDialog.setTitle(R.string.edit_clip_delete_dialog_title)
      .setSubtitle(R.string.edit_clip_delete_dialog_subtitle)
      .setNegativeButton(R.string.negative_close_msg) {
        deleteDialog.dismiss()
      }
      .setPositiveButton(R.string.edit_clip_delete_dialog_delete) {
        viewModel.deleteCategory(itemId)
        /*        viewModel.getCategoryAll()
                updateEditListView()*/
        deleteDialog.dismiss()
      }
      .show()
  }
}
