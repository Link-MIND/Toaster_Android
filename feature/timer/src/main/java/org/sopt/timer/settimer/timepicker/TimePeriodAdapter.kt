package org.sopt.timer.settimer.timepicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.timer.databinding.ItemNumberPickerBinding
import org.sopt.timer.model.PickerItem
import org.sopt.ui.view.ItemDiffCallback

class TimePeriodAdapter : ListAdapter<PickerItem, PickerViewHolder>(DiffUtil) {
  override fun onBindViewHolder(holder: PickerViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickerViewHolder {
    return PickerViewHolder(
      ItemNumberPickerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<PickerItem>(
      onItemsTheSame = { old, new -> old == new },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
