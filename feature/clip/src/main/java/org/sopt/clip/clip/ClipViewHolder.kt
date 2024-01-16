package org.sopt.clip.clip

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.databinding.ItemClipClipBinding
import org.sopt.model.category.Category

class ClipViewHolder(
  private val binding: ItemClipClipBinding,
  private val onClickItemClip: (Long) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(clipData: Category) {
    with(binding) {
      if (clipData == null) return
      val totalClipId: Long = 0
      if (clipData.categoryId == totalClipId) {
        ivClipIcon.setImageResource(org.sopt.mainfeature.R.drawable.ic_clip_all_24)
      }
      tvClipName.text = clipData.categoryTitle
      tvClipAmount.text = clipData.toastNum.toString() + "개"
      root.setOnClickListener {
        onClickItemClip(clipData.categoryId)
      }
    }
  }
}
