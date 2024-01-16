package org.sopt.clip.cliplink

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.clip.ClipViewModel
import org.sopt.clip.DeleteLinkBottomSheetFragment
import org.sopt.clip.LinkDTO
import org.sopt.clip.R
import org.sopt.clip.SelectedToggle
import org.sopt.clip.databinding.FragmentClipLinkBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class ClipLinkFragment : BindingFragment<FragmentClipLinkBinding>({ FragmentClipLinkBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()
  private lateinit var clipLinkAdapter: ClipLinkAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val args: ClipLinkFragmentArgs by navArgs()
    val categoryId = args.categoryId
    Log.d("test", "$categoryId")
    initClipAdapter()
    initViewState(viewModel.mockLinkData.isNullOrEmpty())
  }

  private fun initViewState(isDataNull: Boolean) {
    with(binding) {
      ivClipCategoryEmpty.isVisible = isDataNull
      tvClipLinkEmpty.isVisible = isDataNull

      if (isDataNull) return
      clipLinkAdapter.submitList(viewModel.mockLinkData)

      initToggleClickListener()
      onClickBackButton()
    }
  }

  private fun initClipAdapter() {
    clipLinkAdapter = ClipLinkAdapter(
      { linkDTO ->
        naviagateToWebViewFragment(linkDTO)
      },
    ) { itemId, state ->
      updateItemEvent(state, itemId)
    }
    binding.rvCategoryLink.adapter = clipLinkAdapter
  }

  private fun naviagateToWebViewFragment(linkDTO: LinkDTO) {
    val bundle = Bundle().apply {
      putString("url", linkDTO.url)
    }
    findNavController().navigate(R.id.action_navigation_clip_link_to_webViewFragment, bundle)
  }

  private fun updateItemEvent(state: String, itemId: Long) {
    Toast.makeText(context, "$state itemId: $itemId", Toast.LENGTH_SHORT).show()
    if (state == "delete") {
      DeleteLinkBottomSheetFragment.newInstance(this.id).show(parentFragmentManager, this.tag)
    }
  }

  private fun onClickBackButton() {
    binding.ivClipLinkBack.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun initToggleClickListener(): List<LinkDTO> {
    with(binding) {
      btnClipAll.setOnClickListener {
        updateTogglesNDividerVisible(SelectedToggle.ALL)
      }

      btnClipRead.setOnClickListener {
        updateTogglesNDividerVisible(SelectedToggle.READ)
      }

      btnClipUnread.setOnClickListener {
        updateTogglesNDividerVisible(SelectedToggle.UNREAD)
      }
    }
    return viewModel.mockLinkData
  }

  private fun updateTogglesNDividerVisible(selectedNow: SelectedToggle) {
    updateTogglesVisible(selectedNow)
    initDividerVisible(selectedNow)
  }

  private fun updateTogglesVisible(selectedNow: SelectedToggle) {
    if (selectedNow != viewModel.toggleSelectedPast) {
      initToggleVisible(viewModel.toggleSelectedPast, false)
      initToggleVisible(selectedNow, true)
      initDividerVisible(selectedNow)
      viewModel.toggleSelectedPast = selectedNow
    }
  }

  private fun initToggleVisible(toggle: SelectedToggle, state: Boolean) {
    with(binding) {
      when (toggle) {
        SelectedToggle.ALL -> tvClipAllSelected.isVisible = state
        SelectedToggle.READ -> tvClipReadSelected.isVisible = state
        SelectedToggle.UNREAD -> tvClipUnreadSelected.isVisible = state
      }
    }
  }

  private fun initDividerVisible(selectedNow: SelectedToggle) {
    with(binding) {
      when (selectedNow) {
        SelectedToggle.ALL -> {
          dvClipPicker1.isVisible = false
          dvClipPicker2.isVisible = true
        }

        SelectedToggle.READ -> {
          dvClipPicker1.isVisible = false
          dvClipPicker2.isVisible = false
        }

        SelectedToggle.UNREAD -> {
          dvClipPicker1.isVisible = true
          dvClipPicker2.isVisible = false
        }
      }
    }
  }
}
