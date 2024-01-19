package org.sopt.clip.cliplink

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.toast.linkMindSnackBar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.clip.DeleteLinkBottomSheetFragment
import org.sopt.clip.R
import org.sopt.clip.databinding.FragmentClipLinkBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.fragment.colorOf
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope
import org.sopt.ui.nav.DeepLinkUtil
import org.sopt.ui.view.UiState
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class ClipLinkFragment : BindingFragment<FragmentClipLinkBinding>({ FragmentClipLinkBinding.inflate(it) }) {
  private val viewModel: ClipLinkViewModel by viewModels()
  private lateinit var clipLinkAdapter: ClipLinkAdapter
  var isDataNull: Boolean = true
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val args: ClipLinkFragmentArgs by navArgs()
    val categoryId = args.categoryId
    if (args.categoryId.toInt() == 0) {
      viewModel.getCategoryLink("ALL", 0)
    } else {
      viewModel.getCategoryLink("ALL", categoryId)
    }
    binding.tvClipLinkEntire.setOnClickListener {
      updateState(
        R.id.all,
        { viewModel.getCategoryLink("ALL", args.categoryId) },
        binding.tvClipLinkEntire,
        org.sopt.mainfeature.R.color.neutrals050,
        org.sopt.mainfeature.R.color.neutrals200,
        "전체",
        binding.tvClipLinkRead,
        binding.tvClipLinkUnread,
      )
    }
    binding.tvClipLinkRead.setOnClickListener {
      updateState(
        R.id.read,
        { viewModel.getCategoryLink("READ", args.categoryId) },
        binding.tvClipLinkRead,
        org.sopt.mainfeature.R.color.neutrals050,
        org.sopt.mainfeature.R.color.neutrals050,
        "열람",
        binding.tvClipLinkEntire,
        binding.tvClipLinkUnread,
      )
    }
    binding.tvClipLinkUnread.setOnClickListener {
      updateState(
        R.id.unread,
        { viewModel.getCategoryLink("UNREAD", args.categoryId) },
        binding.tvClipLinkUnread,
        org.sopt.mainfeature.R.color.neutrals200,
        org.sopt.mainfeature.R.color.neutrals050,
        "미열람",
        binding.tvClipLinkEntire,
        binding.tvClipLinkRead,
      )
    }
    initClipAdapter()
    initViewState(isDataNull)
    updateLinkDelete(categoryId)
    updateLinkView()
    updateAllCount()
    onClickBackButton()
  }

  private fun updateState(
    stateId: Int,
    event: () -> Unit,
    selectedTextView: TextView,
    color1: Int,
    color2: Int,
    detailName: String,
    vararg otherTextViews: TextView,
  ) {
    if (binding.mlClipFilter.progress == 0f || binding.mlClipFilter.progress == 1f) {
      binding.mlClipFilter.transitionToState(stateId)
      selectedTextView.setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_bold_14)
      selectedTextView.setTextColor(colorOf(org.sopt.mainfeature.R.color.neutrals850))
      binding.vClipLink1.setBackgroundColor(colorOf(color1))
      binding.vClipLink2.setBackgroundColor(colorOf(color2))
      binding.tvClipCategoryAll.text = detailName
      otherTextViews.forEach { textView ->
        textView.setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_semibold_14)
        textView.setTextColor(colorOf(org.sopt.mainfeature.R.color.neutrals400))
      }
      event()
    }
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
  private fun updateAllCount() {
    viewModel.allClipCount.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          binding.tvClipLinkAllCount.text = "(${state.data})"
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
          requireContext().linkMindSnackBar(binding.vSnack, "링크 삭제 완료", false)
          when (binding.mlClipFilter.currentState) {
            R.id.all -> { viewModel.getCategoryLink("ALL", categoryId) }
            R.id.read -> { viewModel.getCategoryLink("READ", categoryId) }
            R.id.unread -> { viewModel.getCategoryLink("UNREAD", categoryId) }
          }
        }
        is UiState.Failure -> {
          when (binding.mlClipFilter.currentState) {
            R.id.all -> { viewModel.getCategoryLink("ALL", categoryId) }
            R.id.read -> { viewModel.getCategoryLink("READ", categoryId) }
            R.id.unread -> { viewModel.getCategoryLink("UNREAD", categoryId) }
          }
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
          naviagateToWebViewFragment(linkDTO.linkUrl ?: "", linkDTO.toastId, linkDTO.isRead)
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

  private fun naviagateToWebViewFragment(site: String, toastId: Long, isRead: Boolean) {
    navigateToDestination("featureSaveLink://webViewFragment?site=$site,,,$toastId,,,$isRead")
  }

  private fun onClickBackButton() {
    binding.ivClipLinkBack.onThrottleClick {
      findNavController().navigateUp()
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
}
