package org.sopt.clip.clipdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.clip.ClipViewModel
import org.sopt.clip.R
import org.sopt.clip.databinding.FragmentClipDetailBinding
import org.sopt.ui.base.BindingFragment

class ClipDetailFragment : BindingFragment<FragmentClipDetailBinding>({ FragmentClipDetailBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val clipDetailAdapter = ClipLinkAdapter()
    binding.rvCategoryLink.adapter = clipDetailAdapter
    clipDetailAdapter.submitList(viewModel.mockLinkData)

    toggleClickListener()
  }

  fun toggleClickListener() {
    with(binding) {
      btnClipAll.setOnClickListener {
        setButtonTransparent()
        btnClipAll.setBackgroundResource(R.drawable.shape_grey050_fill_12_rect)
      }

      btnClipRead.setOnClickListener {
        setButtonTransparent()
        btnClipRead.setBackgroundResource(R.drawable.shape_grey050_fill_12_rect)
      }

      btnClipUnread.setOnClickListener {
        setButtonTransparent()
        btnClipUnread.setBackgroundResource(R.drawable.shape_grey050_fill_12_rect)
      }
    }
  }

  fun setButtonTransparent() {
    with(binding) {
      btnClipAll.setBackgroundResource(org.sopt.mainfeature.R.color.transparent)
      btnClipRead.setBackgroundResource(org.sopt.mainfeature.R.color.transparent)
      btnClipUnread.setBackgroundResource(org.sopt.mainfeature.R.color.transparent)
    }
  }
}
