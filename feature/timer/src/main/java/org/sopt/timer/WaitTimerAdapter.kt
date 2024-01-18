package org.sopt.timer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.model.timer.Timer
import org.sopt.timer.databinding.ItemTimerWaitBinding
import org.sopt.ui.view.ItemDiffCallback

class WaitTimerAdapter(
  private val onToggleClicked: (Timer) -> Unit,
  private val onMoreClicked: (Timer) -> Unit,
  private val navigateToCategory: (Timer) -> Unit,
) : ListAdapter<Timer, WaitTimerViewHolder>(DiffUtil) {
  override fun onBindViewHolder(holder: WaitTimerViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitTimerViewHolder {
    return WaitTimerViewHolder(
      ItemTimerWaitBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onToggleClicked,
      onMoreClicked,
      navigateToCategory
    )
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<Timer>(
      onItemsTheSame = { old, new -> old.id == new.id },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
