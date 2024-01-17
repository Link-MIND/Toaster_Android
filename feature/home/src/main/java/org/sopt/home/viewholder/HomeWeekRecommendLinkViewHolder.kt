package org.sopt.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import org.sopt.home.databinding.ItemWeekRecommendLinkBinding
import org.sopt.model.home.RecommendLink

class HomeWeekRecommendLinkViewHolder(
  private val binding: ItemWeekRecommendLinkBinding,
  private val onClickRecommendLink: (RecommendLink) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

  fun onBind(data: RecommendLink?) {
    if (data == null) return

    with(binding) {
      binding.tvWeekRecommendLinkTitle.text = data.siteTitle
      binding.tvWeekRecommendSub.text = data.siteUrl
      binding.ivWeekRecommendLink.load(data.siteImg) {
        crossfade(true)
        transformations(CircleCropTransformation())
        root.setOnClickListener {
          onClickRecommendLink(data)
        }
      }
    }
  }
}
