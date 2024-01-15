package org.sopt.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.home.databinding.ItemWeekRecommendLinkBinding
import org.sopt.home.viewholder.HomeWeekRecommendLinkViewHolder
import org.sopt.model.home.RecommendLink
import org.sopt.ui.view.ItemDiffCallback

class HomeWeekRecommendLinkAdapter(
  private val onClickRecommendLink: () -> Unit,
) : ListAdapter<RecommendLink, HomeWeekRecommendLinkViewHolder>(DiffUtil) {
  override fun onBindViewHolder(holder: HomeWeekRecommendLinkViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWeekRecommendLinkViewHolder {
    return HomeWeekRecommendLinkViewHolder(
      ItemWeekRecommendLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onClickRecommendLink,
    )
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<RecommendLink>(
      onItemsTheSame = { old, new -> old.siteId == new.siteId },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
