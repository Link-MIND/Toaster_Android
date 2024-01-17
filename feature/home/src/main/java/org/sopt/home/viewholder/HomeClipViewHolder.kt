package org.sopt.home.viewholder

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.home.databinding.ItemHomeClipBinding
import org.sopt.model.category.Category

class HomeClipViewHolder(
  private val binding: ItemHomeClipBinding,
  private val onClickClip: (Category) -> Unit,
  private val onClickEmptyClip: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: Category?, position: Int) {
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
      tvItemClipTitle.text = data.categoryTitle
      tvItemClipCount.text = data.toastNum.toString() + "개"
      root.setOnClickListener {
        onClickClip(data)
      }
    }
  }
}
