package org.sopt.timer.settimer.timepicker

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.timer.model.PickerItem

class ListUpdater<T : ListAdapter<PickerItem, PickerViewHolder>> {
  fun getNewList(
    snapHolder: CenterSnapHelper,
    recyclerView: RecyclerView,
    isNumber: Boolean,
    adapter: T,
  ): List<PickerItem>? {
    val newList = snapHolder.findSnapView(recyclerView.layoutManager)?.let { centerView ->
      recyclerView.layoutManager?.getPosition(centerView)?.let { pos ->
        val newPos = if (isNumber) pos % adapter.currentList.size else pos
        adapter.currentList.mapIndexed { index, item ->
          item.copy(isSelected = index == newPos)
        }
      }
    }
    return newList
  }
}
