package org.sopt.mainfeature.timer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.mainfeature.databinding.ItemNumberPickerBinding
import org.sopt.mainfeature.timer.dummymodel.PickerItem
import org.sopt.ui.view.ItemDiffCallback

class TextAdapter : ListAdapter<PickerItem, NumberViewHolder>(DiffUtil) {
  override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
    return NumberViewHolder(
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
