package org.sopt.clip.clip

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.ClipsDTO
import org.sopt.clip.databinding.ItemClipClipBinding

class ClipViewHolder(
  private val binding: ItemClipClipBinding,
  private val onClickItemClip: (Long) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(clipData: ClipsDTO?) {
    with(binding) {
      if (clipData == null) {
        return
      } else {
        tvClipName.text = clipData.clipName
        tvClipAmount.text = clipData.clipAmount.toString() + "개"
        root.setOnClickListener {
          onClickItemClip(clipData.clipId)
        }
      }
    }
  }
}
