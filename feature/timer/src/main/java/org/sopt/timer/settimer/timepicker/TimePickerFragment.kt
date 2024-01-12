package org.sopt.timer.settimer.timepicker

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import designsystem.components.button.state.LinkMindButtonState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.timer.R
import org.sopt.timer.databinding.FragmentTimePickerBinding
import org.sopt.timer.dummymodel.PickerItem
import org.sopt.timer.settimer.SetTimerViewModel
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.fragment.colorOf
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope
import org.sopt.ui.view.onThrottleClick

class TimePickerFragment : BindingFragment<FragmentTimePickerBinding>({ FragmentTimePickerBinding.inflate(it) }) {
  private lateinit var timePeriodAdapter: TimePeriodAdapter
  private lateinit var hourAdapter: NumberAdapter
  private lateinit var minuteAdapter: NumberAdapter
  private lateinit var listUpdater: ListUpdater<ListAdapter<PickerItem, PickerViewHolder>>
  private var periodClickEnable = true
  private var hourClickEnable = true
  private var minuteClickEnable = true
  private val viewModel: SetTimerViewModel by activityViewModels()
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    listUpdater = ListUpdater()
    setupRecyclerViews()
    initTimePickerState()
    initRepeatState()
    initRepeatButtonClickListener()
    initBackButtonClickListener()
    initCloseButtonClickListener()
    collectSelectedTime()
  }

  private fun initTimePickerState() {
    if (viewModel.currentHourIndex.value != 1 || viewModel.currentMinuteIndex.value != 1) {
      viewModel.currentHourIndex.value.let {
        binding.rvTimePickerHour.scrollToPosition(minuteAdapter.getMiddlePosition() + it - 1)
      }
      viewModel.currentMinuteIndex.value.let {
        binding.rvTimePickerMinute.scrollToPosition(hourAdapter.getMiddlePosition() + it - 1)
      }
      viewModel.currentAmPmIndex.value.let {
        binding.rvTimePickerAmpm.scrollToPosition(it - 1)
      }
    }
    binding.btnTimePickerNext.state = LinkMindButtonState.DISABLE
    binding.tvTimePickerCategory.text = "카테고리이름을"
  }

  private fun initRepeatState() {
    val list = viewModel.repeatList.value.filter {
      it.isSelected
    }.map {
      it.period
    }
    if (list.isEmpty()) {
      handleEmptyList()
      return
    }
    handleNotEmptyList(list)
  }

  private fun handleEmptyList() {
    with(binding) {
      tvTimePickerPeriod.apply {
        setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_semibold_16)
        setTextColor(colorOf(org.sopt.mainfeature.R.color.neutrals850))
        text = "반복"
      }
      btnTimePickerNext.state = LinkMindButtonState.DISABLE
    }
  }

  private fun handleNotEmptyList(list: List<String>) {
    val modified = list.toString().substring(1, list.toString().length - 1)
    with(binding) {
      tvTimePickerPeriod.apply {
        setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_bold_16)
        setTextColor(colorOf(org.sopt.mainfeature.R.color.primary))
        text = modified
      }
      btnTimePickerNext.state = LinkMindButtonState.ENABLE
    }
  }

  private fun setupRecyclerViews() {
    val ampmList = generateAmpmList()
    val hourList = generateNumberList(1, 12)
    val minuteList = generateNumberList(0, 59)
    val newAmpmList = ampmList.mapIndexed { index, item ->
      item.copy(isSelected = index == viewModel.currentAmPmIndex.value)
    }
    val newHourList = hourList.mapIndexed { index, item ->
      item.copy(isSelected = index == viewModel.currentHourIndex.value)
    }
    val newMinuteList = minuteList.mapIndexed { index, item ->
      item.copy(isSelected = index == viewModel.currentMinuteIndex.value)
    }
    timePeriodAdapter = TimePeriodAdapter()
    hourAdapter = NumberAdapter()
    minuteAdapter = NumberAdapter()
    setupRecyclerView(binding.rvTimePickerAmpm, newAmpmList, timePeriodAdapter, ::updateAmPm) { setPeriodClickEnable(it) }
    setupRecyclerView(binding.rvTimePickerHour, newHourList, hourAdapter, ::updateHour) { setHourClickEnable(it) }
    setupRecyclerView(binding.rvTimePickerMinute, newMinuteList, minuteAdapter, ::updateMinute) { setMinuteClickEnable(it) }
  }

  private fun generateAmpmList() = listOf(
    PickerItem("", false),
    PickerItem("오전", false),
    PickerItem("오후", false),
    PickerItem("", false),
  )

  private fun generateNumberList(start: Int, end: Int): MutableList<PickerItem> {
    val list = mutableListOf<PickerItem>()
    list.add(PickerItem(end.toString(), false))
    list.add(PickerItem(start.toString(), false))
    for (i in start + 1..end - 1) {
      list.add(PickerItem(i.toString(), false))
    }
    return list
  }

  private fun <T : ListAdapter<PickerItem, PickerViewHolder>> setupRecyclerView(
    recyclerView: RecyclerView,
    list: List<PickerItem>,
    adapter: T,
    updateText: (PickerItem, Int) -> Unit,
    setClickEnable: (Boolean) -> Unit,
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
            setClickEnable(false)
            val newList = listUpdater.getNewList(snapHolder, recyclerView, isNumber, adapter)
            adapter.submitList(newList)
            if (newState == 0) {
              newList?.onEach { item ->
                if (item.isSelected) {
                  updateText(item, newList.indexOf(item))
                  setClickEnable(true)
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

  private fun updateHour(item: PickerItem, index: Int) {
    val hour = item.convertToText()
    viewModel.setSelectedHour(hour)
    viewModel.currentHourIndex.value = index
  }

  private fun updateMinute(item: PickerItem, index: Int) {
    val minute = item.convertToText()
    viewModel.setSelectedMinute(minute)
    viewModel.currentMinuteIndex.value = index
  }

  private fun updateAmPm(item: PickerItem, index: Int) {
    val timePeriod = item.convertToText()
    viewModel.setSelectedPeriod(timePeriod)
    viewModel.currentAmPmIndex.value = index
  }

  private fun setPeriodClickEnable(isEnable: Boolean) {
    periodClickEnable = isEnable
  }

  private fun setHourClickEnable(isEnable: Boolean) {
    hourClickEnable = isEnable
  }

  private fun setMinuteClickEnable(isEnable: Boolean) {
    minuteClickEnable = isEnable
  }

  private fun initCloseButtonClickListener() {
    binding.ivTimePickerClose.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun initBackButtonClickListener() {
    binding.ivTimePickerBack.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun initRepeatButtonClickListener() {
    binding.clTimePickerRepeat.onThrottleClick {
      if (periodClickEnable && hourClickEnable && minuteClickEnable) {
        findNavController().navigate(R.id.action_navigation_time_picker_to_navigation_timer_repeat)
      }
    }
  }

  private fun collectSelectedTime() {
    viewModel.selectedTime.flowWithLifecycle(viewLifeCycle).onEach {
      binding.tvTimePickerTime.text = "${it.timePeriod} ${it.hour}시 ${it.minute}분"
    }.launchIn(viewLifeCycleScope)
  }
}
