package org.sopt.clip.clip

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.bottomsheet.LinkMindBottomSheet
import designsystem.components.toast.linkMindSnackBar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.clip.ClipViewModel
import org.sopt.clip.R
import org.sopt.clip.databinding.FragmentClipBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope
import org.sopt.ui.view.UiState
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class ClipFragment : BindingFragment<FragmentClipBinding>({ FragmentClipBinding.inflate(it) }) {
  private val viewModel by activityViewModels<ClipViewModel>()
  private lateinit var clipAdapter: ClipAdapter
  val bundle= Bundle()
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    initClipAdapter()
    if (setEmptyMsgVisible()) return
    viewModel.getCategoryAll()
    updateClipList()
    onClickSearchButton()
/*
    onClickListView()
*/
    onClickEditButton()
    onClickAddButton()
  }

  private fun updateClipList() {
    viewModel.categoryState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          clipAdapter.submitList(state.data)
        }

        else -> {}
      }

    }.launchIn(viewLifeCycleScope)
  }

  private fun initClipAdapter() {
    clipAdapter = ClipAdapter { category ->
      Toast.makeText(context, "클릭된 item id: $category.categoryId", Toast.LENGTH_SHORT).show()
      bundle.putLong("clipId", category.categoryId)
      bundle.putString("clipTitle", category.categoryTitle)
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_link, bundle)

/*
      val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(clipTitle = category.categoryTitle)
        Navigation.findNavController(view!!).navigate(action)
*/

    }
    binding.rvClipClip.adapter = clipAdapter
  }

  private fun setEmptyMsgVisible(): Boolean {
    if (viewModel.mockClipData.isNullOrEmpty()) return true
    binding.ivClipEmpty.visibility = View.GONE
    binding.tvClipEmpty.visibility = View.GONE
    return false
  }

  private fun onClickAddButton() {
    binding.btnClipAdd.onThrottleClick {
      val addClipBottomSheet = LinkMindBottomSheet(requireContext())
      addClipBottomSheet.show()
      addClipBottomSheet.apply {
        setBottomSheetHint(org.sopt.mainfeature.R.string.clip_new_clip_info)
        setTitle(org.sopt.mainfeature.R.string.clip_add_clip)
        setErroMsg(org.sopt.mainfeature.R.string.error_clip_length)
        bottomSheetConfirmBtnClick {
          dismiss()
          viewModel.postAddCategoryTitle(getText())
          requireContext().linkMindSnackBar(binding.root, "클립 생성 완료!", false)
        }
      }
    }
  }

  private fun onClickEditButton() {
    binding.btnClipEdit.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_edit)
    }
  }

/*  private fun onClickListView() {
    bundle.putString("clipTitle",)
    binding.rvClipClip.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_link)
    }
  }*/

  private fun onClickSearchButton() {
    binding.clClipSearch.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_link)
    }
  }
}
