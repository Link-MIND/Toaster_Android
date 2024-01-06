package org.sopt.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.home.databinding.ItemHomeClipBinding
import org.sopt.ui.view.ItemDiffCallback

class HomeClipAdapter(
  private val onClickItemClip: (ClipDummy) -> Unit,
) : ListAdapter<ClipDummy, HomeCliprViewHolder>(DiffUtil) {
  override fun onBindViewHolder(holder: HomeCliprViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCliprViewHolder {
    return HomeCliprViewHolder(
      ItemHomeClipBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onClickItemClip,
    )
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<ClipDummy>(
      onItemsTheSame = { old, new -> old.title == new.title },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
