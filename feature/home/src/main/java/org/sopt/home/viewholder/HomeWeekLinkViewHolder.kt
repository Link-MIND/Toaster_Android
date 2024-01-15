package org.sopt.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.home.WeekLinkDummy
import org.sopt.home.databinding.ItemWeekLinkBinding

class HomeWeekLinkViewHolder(
  private val binding: ItemWeekLinkBinding,
  private val onClickWeekLink: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

  fun onBind(data: WeekLinkDummy?) {
    if (data == null) return

    with(binding) {
      binding.tvWeekLink.text = data.link
      binding.tvWeekLinkTitle.text = data.title
      binding.ivWeekLink.load(data.img)
      root.setOnClickListener {
        onClickWeekLink()
      }
    }
  }
}
