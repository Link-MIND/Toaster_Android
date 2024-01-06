package org.sopt.home

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.home.databinding.ItemHomeClipBinding

class HomeCliprViewHolder(
  private val binding: ItemHomeClipBinding,
  private val onClickItemClip: (ClipDummy) -> Unit,
  private val onClickItemClip2: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

  fun onBind(data: ClipDummy?) {
    if (data == null) {

      with(binding) {
        clItemClip.visibility = View.GONE
        ivEmpty.isVisible = true
        root.setOnClickListener {
          onClickItemClip2()
        }
      }
      return
    }
    with(binding) {
      tvItemClipTitle.text = data.title
      tvItemClipCount.text = data.count.toString() + "개"
      root.setOnClickListener {
        onClickItemClip(data)
      }
    }
  }
}
