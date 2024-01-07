package org.sopt.timer.settimer

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import designsystem.components.button.state.LinkMindButtonState
import org.sopt.timer.databinding.FragmentTimePickerBinding
import org.sopt.timer.dummymodel.PickerItem
import org.sopt.ui.base.BindingFragment

class TimePickerFragment : BindingFragment<FragmentTimePickerBinding>({ FragmentTimePickerBinding.inflate(it) }) {
  private lateinit var textAdapter: TextAdapter
  private lateinit var numberAdapter1: NumberAdapter
  private lateinit var numberAdapter2: NumberAdapter
  private var ampm = "오전"
  private var hour = "01"
  private var minute = "00"
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerViews()
    binding.btnTimePickerNext.state = LinkMindButtonState.DISABLE
    binding.tvTimePickerCategory.text = "카테고리이름을"
    binding.tvTimePickerTime.text = "$ampm ${hour}시 ${minute}분"
  }

  private fun setupRecyclerViews() {
    val ampmList = generateAmpmList()
    val hourList = generateNumberList(1, 12)
    val minuteList = generateNumberList(0, 59)
    textAdapter = TextAdapter()
    numberAdapter1 = NumberAdapter()
    numberAdapter2 = NumberAdapter()
    setupRecyclerView(binding.rvTimePickerAmpm, ampmList, textAdapter, ::updateAmPm)
    setupRecyclerView(binding.rvTimePickerHour, hourList, numberAdapter1, ::updateHour)
    setupRecyclerView(binding.rvTimePickerMinute, minuteList, numberAdapter2, ::updateMinute)
  }

  private fun generateAmpmList() = listOf(
    PickerItem("", false),
    PickerItem("오전", true),
    PickerItem("오후", false),
    PickerItem("", false))

  private fun generateNumberList(start: Int, end: Int): MutableList<PickerItem> {
    val list = mutableListOf<PickerItem>()
    list.add(PickerItem(end.toString(), false))
    list.add(PickerItem(start.toString(), true))
    for (i in start + 1..end - 1) {
      list.add(PickerItem(i.toString(), false))
    }
    return list
  }

  private fun <T : ListAdapter<PickerItem, PickerViewHolder>> setupRecyclerView(
    recyclerView: RecyclerView,
    list: List<PickerItem>,
    adapter: T,
    updateText: (PickerItem) -> Unit,
  ) {
    val snapHolder = CenterSnapHelper().apply {
      attachToRecyclerView(recyclerView)
    }
    val isNumber = adapter is NumberAdapter
    with(adapter) {
      submitList(list)
      recyclerView.adapter = this
    }
    recyclerView.itemAnimator = null
    if (adapter is NumberAdapter) recyclerView.scrollToPosition(adapter.getMiddlePosition())
    recyclerView.apply {
      addOnScrollListener(
        object : RecyclerView.OnScrollListener() {
          override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val newList = getNewList(snapHolder, recyclerView, isNumber, adapter)
            adapter.submitList(newList)
            if (newState == 0) {
              newList?.onEach { item ->
                if (item.isSelected) {
                  updateText(item)
                  binding.tvTimePickerTime.text = "$ampm ${hour}시 ${minute}분"
                }
              }
            }
            adapter.notifyDataSetChanged()
          }
        },
      )
      onFlingListener = object : RecyclerView.OnFlingListener() {
        override fun onFling(velocityX: Int, velocityY: Int): Boolean {
          val newList = getNewList(snapHolder, recyclerView, isNumber, adapter)
          adapter.submitList(newList)
          return false
        }
      }
    }
  }

  private fun <T : ListAdapter<PickerItem, PickerViewHolder>> getNewList(
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

  private fun updateHour(item: PickerItem) {
    hour = item.convertToText()
  }

  private fun updateMinute(item: PickerItem) {
    minute = item.convertToText()
  }

  private fun updateAmPm(item: PickerItem) {
    ampm = item.convertToText()
  }
}
