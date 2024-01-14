package org.sopt.clip.clipdetail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.sopt.clip.ClipViewModel
import org.sopt.clip.LinkDTO
import org.sopt.clip.R
import org.sopt.clip.databinding.FragmentClipDetailBinding
import org.sopt.ui.base.BindingFragment

class ClipDetailFragment : BindingFragment<FragmentClipDetailBinding>({ FragmentClipDetailBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val clipDetailAdapter = ClipLinkAdapter { linkDTO ->
      val bundle = Bundle().apply {
        putString("url", linkDTO.url)
      }
      findNavController().navigate(R.id.action_navigation_clip_detail_to_webViewFragment, bundle)
    }

    binding.rvCategoryLink.adapter = clipDetailAdapter
    var state: Boolean = viewModel.mockLinkData == null
    initEmptyMsgVisible(state)
    if (!state) {
      clipDetailAdapter.submitList(viewModel.mockLinkData)
    }

    initToggleClickListener()
  }

  private fun initToggleClickListener(): List<LinkDTO> {
    with(binding) {
      btnClipAll.setOnClickListener {
        initButtonTransparent()
        initTextGrey()
        dvClipPicker1.isVisible = false
        dvClipPicker2.isVisible = true
        btnClipAll.setBackgroundResource(R.drawable.shape_white_fill_12_rect)
        btnClipAll.setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_bold_14)
      }

      btnClipRead.setOnClickListener {
        initButtonTransparent()
        initTextGrey()
        dvClipPicker1.isVisible = false
        dvClipPicker2.isVisible = false
        btnClipRead.setBackgroundResource(R.drawable.shape_white_fill_12_rect)
        btnClipRead.setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_bold_14)
      }

      btnClipUnread.setOnClickListener {
        initButtonTransparent()
        initTextGrey()
        dvClipPicker1.isVisible = true
        dvClipPicker2.isVisible = false
        btnClipUnread.setBackgroundResource(R.drawable.shape_white_fill_12_rect)
        btnClipUnread.setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_bold_14)
      }
    }
    return viewModel.mockLinkData
  }

  fun initButtonTransparent() {
    with(binding) {
      btnClipAll.setBackgroundResource(org.sopt.mainfeature.R.color.transparent)
      btnClipRead.setBackgroundResource(org.sopt.mainfeature.R.color.transparent)
      btnClipUnread.setBackgroundResource(org.sopt.mainfeature.R.color.transparent)
    }
  }

  fun initEmptyMsgVisible(state: Boolean) {
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
