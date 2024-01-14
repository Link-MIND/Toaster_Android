package org.sopt.clip.cliplink

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.bottomsheet.LinkMindBottomSheet
import org.sopt.clip.ClipViewModel
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
    clipLinkAdapter = ClipLinkAdapter(
      { linkDTO ->
        val bundle = Bundle().apply {
          putString("url", linkDTO.url)
        }
        findNavController().navigate(R.id.action_navigation_clip_detail_to_webViewFragment, bundle)
      },
    ) { itemId, state ->
      Toast.makeText(context, "$state itemId: $itemId", Toast.LENGTH_SHORT).show()
      if (state == "delete") {
        val deleteLinkBottomSheet = LinkMindBottomSheet(requireContext())
        deleteLinkBottomSheet.show()
      }
    }
    binding.rvCategoryLink.adapter = clipLinkAdapter

    var state: Boolean = viewModel.mockLinkData == null
    initEmptyMsgVisible(state)
    if (!state) {
      clipLinkAdapter.submitList(viewModel.mockLinkData)

      updateListView()

      initToggleClickListener()
      onClickBackButton()
    }
  }

  private fun onClickBackButton() {
    binding.ivClipDetailBack.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun updateListView() {
    viewModel.mockDataListState.observe(
      viewLifecycleOwner,
    ) {
      initEmptyMsgVisible(it)
      if (!it) {
        clipLinkAdapter.submitList(viewModel.mockLinkData)
      }
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
      updateListView()
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

  private fun initEmptyMsgVisible(state: Boolean) {
    with(binding) {
      ivClipCategoryEmpty.isVisible = state
      tvClipDetailEmpty.isVisible = state
    }
  }

  fun initTextGrey() {
    with(binding) {
      btnClipAll.setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_semibold_14)
      btnClipRead.setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_semibold_14)
      btnClipUnread.setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_semibold_14)
      btnClipAll.setTextColor(ContextCompat.getColor(root.context, org.sopt.mainfeature.R.color.neutrals400))
      btnClipRead.setTextColor(ContextCompat.getColor(root.context, org.sopt.mainfeature.R.color.neutrals400))
      btnClipUnread.setTextColor(ContextCompat.getColor(root.context, org.sopt.mainfeature.R.color.neutrals400))
    }
  }
}
