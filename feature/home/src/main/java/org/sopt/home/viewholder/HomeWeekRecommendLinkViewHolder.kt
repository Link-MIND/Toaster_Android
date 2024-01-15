package org.sopt.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import org.sopt.home.WeekLinkDummy
import org.sopt.home.databinding.ItemWeekRecommendLinkBinding

class HomeWeekRecommendLinkViewHolder(
  private val binding: ItemWeekRecommendLinkBinding,
  private val onClickRecommendLink: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

  fun onBind(data: WeekLinkDummy?) {
    if (data == null) return

    with(binding) {
      binding.tvWeekRecommendLinkTitle.text = data.title
      binding.tvWeekRecommendSub.text = data.link
      binding.ivWeekRecommendLink.load(data.img) {
        crossfade(true)
        transformations(CircleCropTransformation())
        root.setOnClickListener {
          onClickRecommendLink()
        }
      }
    }

  }

}
