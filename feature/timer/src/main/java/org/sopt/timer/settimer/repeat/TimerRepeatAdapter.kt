package org.sopt.timer.settimer.repeat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.timer.databinding.ItemTimerRepeatBinding
import org.sopt.model.timer.Repeat
import org.sopt.ui.view.ItemDiffCallback

class TimerRepeatAdapter(
    private val onClick: (Repeat, Int) -> Unit,
    private val context: Context,
) : ListAdapter<Repeat, TimerRepeatViewHolder>(DiffUtil) {
  override fun onBindViewHolder(holder: TimerRepeatViewHolder, position: Int) {
    holder.onBind(getItem(position), onClick)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerRepeatViewHolder {
    return TimerRepeatViewHolder(
      ItemTimerRepeatBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      context,
    )
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<Repeat>(
      onItemsTheSame = { old, new -> old == new },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
