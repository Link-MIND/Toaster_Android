package org.sopt.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.home.databinding.ItemWeekLinkBinding
import org.sopt.model.home.WeekBestLink

class HomeWeekLinkViewHolder(
  private val binding: ItemWeekLinkBinding,
  private val onClickWeekLink: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

  fun onBind(data: WeekBestLink?) {
    if (data == null) return

    with(binding) {
      binding.tvWeekLink.text = data.toastLink
      binding.tvWeekLinkTitle.text = data.toastTitle
      binding.ivWeekLink.load(data.toastImg)
      root.setOnClickListener {
        onClickWeekLink()
      }
    }
  }
}
