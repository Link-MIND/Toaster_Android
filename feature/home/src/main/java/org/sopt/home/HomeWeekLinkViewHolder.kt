package org.sopt.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.home.databinding.ItemWeekLinkBinding

class HomeWeekLinkViewHolder(
  private val binding: ItemWeekLinkBinding,
  private val onClickItem: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

  fun onBind(data: WeekLinkDummy?) {
    if (data == null) return

    with(binding) {
      binding.tvWeekLink.text = data.link
      binding.tvWeekLinkTitle.text = data.title
      binding.ivWeekLink.load(data.img)
      root.setOnClickListener {
        onClickItem()
      }
    }
  }
}
