package org.sopt.clip.clipedit

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.databinding.ItemClipEditClipBinding
import org.sopt.model.category.Category

class ClipEditViewHolder(
  private val binding: ItemClipEditClipBinding,
  private val onClickItemClip: (Long, String, Long) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(clipData: Category) {
    if (clipData == null) return
    with(binding) {
      tvClipEditTitle.text = clipData.categoryTitle
      if (clipData.categoryTitle == "전체 클립") {
        ivClipEditDelete.setImageResource(org.sopt.mainfeature.R.drawable.ic_pin_24)
        ivClipEditTitleEdit.visibility = View.GONE
      } else {
        ivClipEditTitleEdit.setOnClickListener {
          onClickItemClip(clipData.categoryId, "edit", itemId)
        }
        ivClipEditDelete.setOnClickListener {
          onClickItemClip(clipData.categoryId, "delete", itemId)
        }
      }
    }
  }
}
