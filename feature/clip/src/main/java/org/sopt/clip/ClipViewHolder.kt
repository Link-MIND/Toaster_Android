package org.sopt.clip

import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.databinding.ItemClipClipBinding

class ClipViewHolder(
  private val binding: ItemClipClipBinding,
  private val onClickItemClip: (ClipsDTO) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(clipData: ClipsDTO?) {
    if (clipData != null) {
      with(binding) {
        tvClipName.text = clipData.clipName
        tvClipAmount.text = clipData.clipAmount.toString() + "개"
        root.setOnClickListener {
          onClickItemClip(clipData)
        }
      }
      return
    }
  }
}
