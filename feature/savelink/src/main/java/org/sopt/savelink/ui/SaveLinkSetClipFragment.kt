package org.sopt.savelink.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import designsystem.components.bottomsheet.LinkMindBottomSheet
import designsystem.components.button.state.LinkMindButtonState
import designsystem.components.dialog.LinkMindDialog
import org.sopt.mainfeature.R
import org.sopt.savelink.databinding.FragmentSaveLinkSetClipBinding
import org.sopt.savelink.ui.adapter.ClipSelectAdapter
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.nav.DeepLinkUtil
import org.sopt.ui.view.onThrottleClick

class SaveLinkSetClipFragment : BindingFragment<FragmentSaveLinkSetClipBinding>({ FragmentSaveLinkSetClipBinding.inflate(it) }) {

  private lateinit var adapter: ClipSelectAdapter
  private val linkMindDialog by lazy {
    LinkMindDialog(requireContext())
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.btnSaveLinkComplete.state = LinkMindButtonState.DISABLE
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
      context = requireContext(),
    )
    adapter.submitList(list)
    binding.rvItemTimerClipSelect.adapter = adapter

    binding.tvSaveLinkAddClip.onThrottleClick {
      val linkMindBottomSheet = LinkMindBottomSheet(requireContext())
      linkMindBottomSheet.show()
      linkMindBottomSheet.apply {
        setTitle(R.string.text_clip)
        setErroMsg(R.string.text_clip)
        bottomSheetConfirmBtnClick {
        }
      }
    }
    binding.ivSaveLinkClose.onThrottleClick {
      linkMindDialog.setTitle(R.string.text_home)
        .setSubtitle(R.string.text_clip)
        .setNegativeButton(R.string.text_home) {
          linkMindDialog.dismiss()
        }
        .setPositiveButton(R.string.text_home) {
          linkMindDialog.dismiss()
        }
        .show()
    }
    binding.btnSaveLinkComplete.btnClick {
      val (request, navOptions) = DeepLinkUtil.getNavRequestPopUpAndOption(
        findNavController().graph.id,
        false,
        "featureHome://homeFragment",
      )
      findNavController().navigate(request, navOptions)
    }
  }
}
