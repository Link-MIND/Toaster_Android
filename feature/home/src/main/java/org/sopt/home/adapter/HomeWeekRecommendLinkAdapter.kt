package org.sopt.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.home.viewholder.HomeWeekLinkViewHolder
import org.sopt.home.WeekLinkDummy
import org.sopt.home.databinding.ItemWeekLinkBinding
import org.sopt.home.databinding.ItemWeekRecommendLinkBinding
import org.sopt.home.viewholder.HomeWeekRecommendLinkViewHolder
import org.sopt.ui.view.ItemDiffCallback

class HomeWeekRecommendLinkAdapter(
  private val onClickItem: () -> Unit,
) : ListAdapter<WeekLinkDummy, HomeWeekRecommendLinkViewHolder>(DiffUtil) {
  override fun onBindViewHolder(holder: HomeWeekRecommendLinkViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWeekRecommendLinkViewHolder {
    return HomeWeekRecommendLinkViewHolder(
      ItemWeekRecommendLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onClickItem,
    )
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<WeekLinkDummy>(
      onItemsTheSame = { old, new -> old.title == new.title },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
