package org.sopt.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.home.databinding.ItemWeekLinkBinding
import org.sopt.home.viewholder.HomeWeekLinkViewHolder
import org.sopt.model.home.WeekBestLink
import org.sopt.ui.view.ItemDiffCallback

class HomeWeekLinkAdapter(
  private val onClickWeekLink: () -> Unit,
) : ListAdapter<WeekBestLink, HomeWeekLinkViewHolder>(DiffUtil) {
  override fun onBindViewHolder(holder: HomeWeekLinkViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWeekLinkViewHolder {
    return HomeWeekLinkViewHolder(
      ItemWeekLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onClickWeekLink,
    )
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<WeekBestLink>(
      onItemsTheSame = { old, new -> old.toastId == new.toastId },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
