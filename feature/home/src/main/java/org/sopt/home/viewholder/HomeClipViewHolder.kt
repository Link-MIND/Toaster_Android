package org.sopt.home.viewholder

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.home.ClipDummy
import org.sopt.home.databinding.ItemHomeClipBinding

class HomeClipViewHolder(
  private val binding: ItemHomeClipBinding,
  private val onClickClip: (ClipDummy) -> Unit,
  private val onClickEmptyClip: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: ClipDummy?, position: Int) {
    if (position == 0) {
      binding.ivHomeClip.setImageResource(org.sopt.mainfeature.R.drawable.ic_clip_all_24)
    }
    if (data == null) {
      with(binding) {
        clItemClip.isGone = true
        clItemClipEmpty.isVisible = true
        root.setOnClickListener {
          onClickEmptyClip()
        }
      }
      return
    }
    with(binding) {
      tvItemClipTitle.text = data.title
      tvItemClipCount.text = data.count.toString() + "개"
      root.setOnClickListener {
        onClickClip(data)
      }
    }
  }
}
