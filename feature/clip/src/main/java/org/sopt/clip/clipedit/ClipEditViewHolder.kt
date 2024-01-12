package org.sopt.clip.clipedit

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.ClipsDTO
import org.sopt.clip.databinding.ItemClipEditClipBinding

class ClipEditViewHolder(
  private val binding: ItemClipEditClipBinding,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(clipData: ClipsDTO) {
    if (clipData == null) return
    with(binding) {
      tvClipEditTitle.text = clipData.clipName
      clClipEditClip.setOnClickListener {
        // id 전달
      }
      ivClipEditDelete.setOnClickListener {
        // id 전달
      }
    }
  }
}
