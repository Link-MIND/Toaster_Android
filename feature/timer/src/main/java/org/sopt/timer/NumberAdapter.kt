package org.sopt.timer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.timer.databinding.ItemNumberPickerBinding
import org.sopt.timer.dummymodel.PickerItem
import org.sopt.ui.view.ItemDiffCallback
import kotlin.math.round

class NumberAdapter : ListAdapter<PickerItem, NumberViewHolder>(DiffUtil) {
  override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
    val realPosition = position % currentList.size
    holder.onBind(getItem(realPosition))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
    return NumberViewHolder(
      ItemNumberPickerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )
  }

  override fun getItemCount(): Int = Int.MAX_VALUE

  fun getMiddlePosition() =
    round(Int.MAX_VALUE / 2.0).toInt() - round(Int.MAX_VALUE / 2.0).toInt() % currentList.size

  companion object {
    private val DiffUtil = ItemDiffCallback<PickerItem>(
      onItemsTheSame = { old, new -> old == new },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
