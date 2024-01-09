package org.sopt.timer.settimer.timepicker

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import designsystem.components.button.state.LinkMindButtonState
import org.sopt.timer.R
import org.sopt.timer.databinding.FragmentTimePickerBinding
import org.sopt.timer.dummymodel.PickerItem
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class TimePickerFragment : BindingFragment<FragmentTimePickerBinding>({ FragmentTimePickerBinding.inflate(it) }) {
  private lateinit var timePeriodAdapter: TimePeriodAdapter
  private lateinit var hourAdapter: NumberAdapter
  private lateinit var minuteAdapter: NumberAdapter
  private lateinit var listUpdater: ListUpdater<ListAdapter<PickerItem, PickerViewHolder>>
  private var timePeriod = "오전"
  private var hour = "01"
  private var minute = "00"
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerViews()
    listUpdater = ListUpdater()
    binding.btnTimePickerNext.state = LinkMindButtonState.DISABLE
    binding.tvTimePickerCategory.text = "카테고리이름을"
    binding.tvTimePickerTime.text = "$timePeriod ${hour}시 ${minute}분"

    binding.clTimePickerRepeat.onThrottleClick {
      findNavController().navigate(R.id.action_navigation_time_picker_to_navigation_timer_repeat)
    }
  }

  private fun setupRecyclerViews() {
    val ampmList = generateAmpmList()
    val hourList = generateNumberList(1, 12)
    val minuteList = generateNumberList(0, 59)
    timePeriodAdapter = TimePeriodAdapter()
    hourAdapter = NumberAdapter()
    minuteAdapter = NumberAdapter()
    setupRecyclerView(binding.rvTimePickerAmpm, ampmList, timePeriodAdapter, ::updateAmPm)
    setupRecyclerView(binding.rvTimePickerHour, hourList, hourAdapter, ::updateHour)
    setupRecyclerView(binding.rvTimePickerMinute, minuteList, minuteAdapter, ::updateMinute)
  }

  private fun generateAmpmList() = listOf(
    PickerItem("", false),
    PickerItem("오전", true),
    PickerItem("오후", false),
    PickerItem("", false),
  )

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
            val newList = listUpdater.getNewList(snapHolder, recyclerView, isNumber, adapter)
            adapter.submitList(newList)
            if (newState == 0) {
              newList?.onEach { item ->
                if (item.isSelected) {
                  updateText(item)
                  binding.tvTimePickerTime.text = "$timePeriod ${hour}시 ${minute}분"
                }
              }
            }
            adapter.notifyDataSetChanged()
          }
        },
      )
      onFlingListener = object : RecyclerView.OnFlingListener() {
        override fun onFling(velocityX: Int, velocityY: Int): Boolean {
          val newList = listUpdater.getNewList(snapHolder, recyclerView, isNumber, adapter)
          adapter.submitList(newList)
          return false
        }
      }
    }
  }

  private fun updateHour(item: PickerItem) {
    hour = item.convertToText()
  }

  private fun updateMinute(item: PickerItem) {
    minute = item.convertToText()
  }

  private fun updateAmPm(item: PickerItem) {
    timePeriod = item.convertToText()
  }
}
