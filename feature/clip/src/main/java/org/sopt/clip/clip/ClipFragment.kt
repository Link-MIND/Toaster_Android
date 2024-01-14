package org.sopt.clip.clip

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.bottomsheet.LinkMindBottomSheet
import designsystem.components.toast.linkMindSnackBar
import org.sopt.clip.ClipViewModel
import org.sopt.clip.R
import org.sopt.clip.databinding.FragmentClipBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class ClipFragment : BindingFragment<FragmentClipBinding>({ FragmentClipBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val clipAdapter = ClipAdapter { clipId ->
      Toast.makeText(context, "클릭된 item id: $clipId", Toast.LENGTH_SHORT).show()
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_link)
    }
    if (setListVisible(clipAdapter)) return

    clipAdapter.submitList(viewModel.mockClipData)
    onClickSearchButton()
    onClickListView()
    onClickEditButton()
    onClickAddButton()
  }

  private fun setListVisible(clipAdapter: ClipAdapter): Boolean {
    binding.rvClipClip.adapter = clipAdapter
    if (viewModel.mockClipData == null) return true
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

  private fun onClickListView() {
    binding.rvClipClip.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_link)
    }
  }

  private fun onClickSearchButton() {
    binding.clClipSearch.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_clip_to_navigation_clip_link)
    }
  }
}
