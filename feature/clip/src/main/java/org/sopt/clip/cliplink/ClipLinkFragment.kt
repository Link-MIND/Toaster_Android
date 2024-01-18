package org.sopt.clip.cliplink

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.clip.ClipViewModel
import org.sopt.clip.DeleteLinkBottomSheetFragment
import org.sopt.clip.SelectedToggle
import org.sopt.clip.databinding.FragmentClipLinkBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope
import org.sopt.ui.nav.DeepLinkUtil
import org.sopt.ui.view.UiState
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class ClipLinkFragment : BindingFragment<FragmentClipLinkBinding>({ FragmentClipLinkBinding.inflate(it) }) {
  private val viewModel : ClipLinkViewModel by viewModels()
  private lateinit var clipLinkAdapter: ClipLinkAdapter
  var readFilter: String = "ALL"
  var isDataNull: Boolean = true
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val args: ClipLinkFragmentArgs by navArgs()
    val categoryId = args.categoryId
    if (args.categoryId.toInt() == 0) {
      viewModel.getCategoryLink(readFilter, 0)
    } else {
      viewModel.getCategoryLink(readFilter, categoryId)
    }
    initClipAdapter()
    initViewState(isDataNull)
    updateLinkDelete(categoryId)
    updateLinkView()
    updateAllClipCount()
    initToggleClickListener()
    onClickBackButton()
  }

  private fun updateLinkView() {
    viewModel.linkState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          clipLinkAdapter.submitList(state.data)
          initViewState(state.data.isNullOrEmpty())
        }

        else -> {
          initViewState(true)
        }
      }
    }.launchIn(viewLifeCycleScope)
  }

  private fun updateLinkDelete(categoryId: Long) {
    viewModel.deleteState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          viewModel.getCategoryLink(readFilter, categoryId)
        }

        else -> {
          initViewState(true)
        }
      }
    }.launchIn(viewLifeCycleScope)
  }
  private fun initViewState(isDataNull: Boolean) {
    with(binding) {
      ivClipCategoryEmpty.isVisible = isDataNull
      tvClipLinkEmpty.isVisible = isDataNull
    }
  }

  private fun initClipAdapter() {
    clipLinkAdapter = ClipLinkAdapter { linkDTO, state ->
      when (state) {
        "click" -> {
          naviagateToWebViewFragment(linkDTO.linkUrl ?: "")
        }

        "delete" -> {
          DeleteLinkBottomSheetFragment.newInstance(
            linkDTO.toastId.toInt(),
            handleDeleteButton = {
              viewModel.deleteLink(linkDTO.toastId)
            },
          ).show(parentFragmentManager, this.tag)
        }
      }
    }
    binding.rvCategoryLink.adapter = clipLinkAdapter
  }

  private fun naviagateToWebViewFragment(site: String) {
    navigateToDestination("featureSaveLink://webViewFragment?site=$site")
  }

  private fun onClickBackButton() {
    binding.ivClipLinkBack.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun initToggleClickListener() {
    with(binding) {
      btnClipAll.setOnClickListener {
        updateTogglesNDividerVisible(SelectedToggle.ALL)
        updateLinkView()
      }

      btnClipRead.setOnClickListener {
        updateTogglesNDividerVisible(SelectedToggle.READ)
        updateLinkView()
      }

      btnClipUnread.setOnClickListener {
        updateTogglesNDividerVisible(SelectedToggle.UNREAD)
        updateLinkView()
      }
    }
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
  private fun updateAllClipCount() {
    viewModel.allClipCount.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          binding.tvClipLinkAllCount.text= "(${state.data})"
        }

        else -> {}
      }
    }.launchIn(viewLifeCycleScope)
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

  private fun navigateToDestination(destination: String) {
    val (request, navOptions) = DeepLinkUtil.getNavRequestNotPopUpAndOption(
      destination,
      enterAnim = org.sopt.mainfeature.R.anim.from_bottom,
      exitAnim = android.R.anim.fade_out,
      popEnterAnim = android.R.anim.fade_in,
      popExitAnim = org.sopt.mainfeature.R.anim.to_bottom,
    )
    findNavController().navigate(request, navOptions)
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
