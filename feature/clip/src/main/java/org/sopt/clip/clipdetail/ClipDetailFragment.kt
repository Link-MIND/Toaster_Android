package org.sopt.clip.clipdetail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.sopt.clip.ClipViewModel
import org.sopt.clip.LinkDTO
import org.sopt.clip.databinding.FragmentClipDetailBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class ClipDetailFragment : BindingFragment<FragmentClipDetailBinding>({ FragmentClipDetailBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()
  private lateinit var clipDetailAdapter: ClipLinkAdapter
  private var toggleSelectedPast: Int? = 1

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initLinkAdapter()
    updateListView()

    initToggleClickListener()
    onClickBackButton()
  }

  private fun onClickBackButton() {
    binding.ivClipDetailBack.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun initLinkAdapter() {
    with(binding) {
      clipDetailAdapter = ClipLinkAdapter()
      rvCategoryLink.adapter = clipDetailAdapter
    }
  }

  private fun updateListView() {
    var state: Boolean = viewModel.mockLinkData == null
    initEmptyMsgVisible(state)
    if (!state) {
      clipDetailAdapter.submitList(viewModel.mockLinkData)
    }
  }

  private fun initToggleClickListener(): List<LinkDTO> {
    with(binding) {
      btnClipAll.setOnClickListener {
        updateTogglesNDividerVisible(toggleSelectedPast, 1)
      }

      btnClipRead.setOnClickListener {
        updateTogglesNDividerVisible(toggleSelectedPast, 2)
      }

      btnClipUnread.setOnClickListener {
        updateTogglesNDividerVisible(toggleSelectedPast, 3)
      }
    }
    return viewModel.mockLinkData
  }

  private fun updateTogglesNDividerVisible(selectedPast: Int?, selectedNow: Int?) {
    updateTogglesVisible(selectedPast, selectedNow)
    initDividerVisible(selectedNow)
  }

  private fun updateTogglesVisible(selectedPast: Int?, selectedNow: Int?) {
    if (selectedNow != selectedPast) {
      initToggleVisible(selectedPast, false)
      initToggleVisible(selectedNow, true)
      initDividerVisible(selectedNow)
      toggleSelectedPast = selectedNow
      updateListView()
    }
  }

  private fun initToggleVisible(toggle: Int?, state: Boolean) {
    with(binding) {
      when (toggle) {
        1 -> tvClipAllSelected.isVisible = state
        2 -> tvClipReadSelected.isVisible = state
        3 -> tvClipUnreadSelected.isVisible = state
        else -> {}
      }
    }
  }

  private fun initDividerVisible(selectedNow: Int?) {
    with(binding) {
      when (selectedNow) {
        1 -> {
          dvClipPicker1.isVisible = false
          dvClipPicker2.isVisible = true
        }

        2 -> {
          dvClipPicker1.isVisible = false
          dvClipPicker2.isVisible = false
        }

        3 -> {
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
}
