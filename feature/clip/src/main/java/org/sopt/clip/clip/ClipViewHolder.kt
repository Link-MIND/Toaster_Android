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
      if (clipData == null) return
      val totalClipId: Long = 0
      if (clipData.clipId == totalClipId) {
        ivClipIcon.setImageResource(org.sopt.mainfeature.R.drawable.ic_clip_all_24)
      }
      tvClipName.text = clipData.clipName
      tvClipAmount.text = clipData.clipAmount.toString() + "개"
      root.setOnClickListener {
        onClickItemClip(clipData.clipId)
      }
    }
  }
}
