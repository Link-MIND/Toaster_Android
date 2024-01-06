package org.sopt.home

import androidx.recyclerview.widget.RecyclerView
import org.sopt.home.databinding.ItemHomeClipBinding

class HomeCliprViewHolder(
  private val binding: ItemHomeClipBinding,
  private val onClickItemClip: (ClipDummy) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

  fun onBind(data: ClipDummy?) {
    if (data == null) return
    with(binding) {
      tvItemClipTitle.text = data.title
      tvItemClipCount.text = data.count.toString()+"개"
      root.setOnClickListener {
        onClickItemClip(data)
      }
    }
  }
}
