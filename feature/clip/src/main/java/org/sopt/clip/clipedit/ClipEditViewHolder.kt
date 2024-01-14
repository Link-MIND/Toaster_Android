package org.sopt.clip.clipedit

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.ClipsDTO
import org.sopt.clip.databinding.ItemClipEditClipBinding

class ClipEditViewHolder(
  private val binding: ItemClipEditClipBinding,
  private val onClickItemClip: (Long, String, Long) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(clipData: ClipsDTO) {
    if (clipData == null) return
    with(binding) {
      tvClipEditTitle.text = clipData.clipName
      ivClipEditTitleEdit.setOnClickListener {
        onClickItemClip(clipData.clipId, "edit", itemId)
      }
      ivClipEditDelete.setOnClickListener {
        onClickItemClip(clipData.clipId, "delete", itemId)
      }
    }
  }
}
