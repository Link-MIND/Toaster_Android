package org.sopt.savelink.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.bottomsheet.LinkMindBottomSheet
import designsystem.components.button.state.LinkMindButtonState
import designsystem.components.dialog.LinkMindDialog
import designsystem.components.toast.linkMindSnackBar
import org.sopt.mainfeature.R
import org.sopt.savelink.databinding.FragmentSaveLinkSetClipBinding
import org.sopt.savelink.ui.adapter.ClipSelectAdapter
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.nav.DeepLinkUtil
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class SaveLinkSetClipFragment : BindingFragment<FragmentSaveLinkSetClipBinding>({ FragmentSaveLinkSetClipBinding.inflate(it) }) {

  private val viewModel: SaveLinkViewModel by viewModels()
  private lateinit var adapter: ClipSelectAdapter
  private val linkMindDialog by lazy {
    LinkMindDialog(requireContext())
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.getCategortAll()
//    viewModel.deleteLink(23)
//    viewModel.saveLink("http://www.naver.com",null)
//    viewModel.patchReadLink(23)
    binding.btnSaveLinkComplete.state = LinkMindButtonState.DISABLE
    binding.ivSaveLinkClipBack.onThrottleClick {
      findNavController().navigateUp()
    }
    var list = listOf(
      Clip("전체 클립", 3, false),
      Clip("전체 클립", 3, false),
      Clip("전체 클립", 3, false),
      Clip("전체 클립", 3, false),
    )

    adapter = ClipSelectAdapter(
      onClick = { a, b ->
        if (a.isSelected) {
          list.onEach { it.isSelected = false }
          list[b].isSelected = true
          binding.btnSaveLinkComplete.state = LinkMindButtonState.ENABLE
        } else {
          list.onEach { it.isSelected = false }
          binding.btnSaveLinkComplete.state = LinkMindButtonState.DISABLE
        }
      },
    )
    adapter.submitList(list)
    binding.rvItemTimerClipSelect.adapter = adapter

    binding.tvSaveLinkAddClip.onThrottleClick {
      val linkMindBottomSheet = LinkMindBottomSheet(requireContext())
      linkMindBottomSheet.show()
      linkMindBottomSheet.apply {
        setTitle(R.string.clip_add_clip)
        setErroMsg(R.string.error_clip_length)
        bottomSheetConfirmBtnClick {
          if (showErrorMsg()) return@bottomSheetConfirmBtnClick
          dismiss()
        }
      }
    }

    binding.ivSaveLinkClose.onThrottleClick {
      linkMindDialog.setTitle(R.string.save_clip_dialog_title)
        .setSubtitle(R.string.save_clip_dialog_sub_title)
        .setNegativeButton(R.string.negative_close_msg) {
          linkMindDialog.dismiss()
          navigateToHome()
        }
        .setPositiveButton(R.string.positive_ok_msg) {
          linkMindDialog.dismiss()
        }
        .show()
    }

    binding.btnSaveLinkComplete.apply {
      btnClick {
        if (state == LinkMindButtonState.DISABLE) return@btnClick
        viewModel.saveLink("www.naver.com",11)
        navigateToHome()
        requireContext().linkMindSnackBar(binding.root, "링크 저장 완료", false)
      }
    }
  }

  private fun navigateToHome() {
    val (request, navOptions) = DeepLinkUtil.getNavRequestPopUpAndAnimption(
      findNavController().graph.id,
      false,
      "featureHome://homeFragment",
      enterAnim = R.anim.from_bottom,
      exitAnim = android.R.anim.fade_out,
      popEnterAnim = android.R.anim.fade_in,
      popExitAnim = R.anim.to_bottom,
    )
    findNavController().navigate(request, navOptions)
  }
}
