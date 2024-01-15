package org.sopt.timer.settimer.timepicker

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.button.state.LinkMindButtonState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.timer.R
import org.sopt.timer.TimerViewModel
import org.sopt.timer.databinding.FragmentTimePickerBinding
import org.sopt.timer.model.PickerItem
import org.sopt.timer.settimer.SetTimerViewModel
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.fragment.colorOf
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope
import org.sopt.ui.view.UiState
import org.sopt.ui.view.onThrottleClick

@AndroidEntryPoint
class TimePickerFragment : BindingFragment<FragmentTimePickerBinding>({ FragmentTimePickerBinding.inflate(it) }) {
  private lateinit var timePeriodAdapter: TimePeriodAdapter
  private lateinit var hourAdapter: NumberAdapter
  private lateinit var minuteAdapter: NumberAdapter
  private lateinit var listUpdater: ListUpdater<ListAdapter<PickerItem, PickerViewHolder>>
  private var periodClickEnable = true
  private var hourClickEnable = true
  private var minuteClickEnable = true
  private val setTimerViewModel: SetTimerViewModel by activityViewModels()
  private val timerViewModel: TimerViewModel by activityViewModels()
  val args: TimePickerFragmentArgs by navArgs()
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
    initCompleteButtonClickListener()
    collectPostTimerState()
  }

  private fun initTimePickerState() {

    if (setTimerViewModel.currentHourIndex.value != 1 || setTimerViewModel.currentMinuteIndex.value != 1) {
      setTimerViewModel.currentHourIndex.value.let {
        binding.rvTimePickerHour.scrollToPosition(minuteAdapter.getMiddlePosition() + it - 1)
      }
      setTimerViewModel.currentMinuteIndex.value.let {
        binding.rvTimePickerMinute.scrollToPosition(hourAdapter.getMiddlePosition() + it - 1)
      }
      setTimerViewModel.currentAmPmIndex.value.let {
        binding.rvTimePickerAmpm.scrollToPosition(it - 1)
      }
    }
    binding.btnTimePickerNext.state = LinkMindButtonState.DISABLE
    binding.btnTimePickerNext.isClickable = false
    binding.tvTimePickerCategory.text = if (args.argPatch) "${args.argCategoryName}클립을" else "카테고리이름클립을"
  }

  private fun initRepeatState() {
    val list = setTimerViewModel.selectedList.value
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
      btnTimePickerNext.isClickable = false
    }
  }

  private fun handleNotEmptyList(list: List<String>) {
    val modified = list.joinToString(separator = ", ")
    with(binding) {
      tvTimePickerPeriod.apply {
        setTextAppearance(org.sopt.mainfeature.R.style.Typography_suit_bold_16)
        setTextColor(colorOf(org.sopt.mainfeature.R.color.primary))
        text = modified
      }
      btnTimePickerNext.state = LinkMindButtonState.ENABLE
      btnTimePickerNext.isClickable = true
    }
  }

  private fun setupRecyclerViews() {
    setUpTimePeriodRecyclerView()
    setUpHourRecyclerView()
    setUpMinuteRecyclerView()
  }

  private fun setUpMinuteRecyclerView() {
    val minuteList = generateNumberList(0, 59)
    val newMinuteList = minuteList.mapIndexed { index, item ->
      item.copy(isSelected = index == setTimerViewModel.currentMinuteIndex.value)
      }
    minuteAdapter = NumberAdapter()
    setupRecyclerView(binding.rvTimePickerMinute, newMinuteList, minuteAdapter, ::updateMinute) { setMinuteClickEnable(it) }
  }

  private fun setUpHourRecyclerView() {
    val hourList = generateNumberList(1, 12)
    val newHourList = hourList.mapIndexed { index, item ->
      item.copy(isSelected = index == setTimerViewModel.currentHourIndex.value)
      }
    hourAdapter = NumberAdapter()
    setupRecyclerView(binding.rvTimePickerHour, newHourList, hourAdapter, ::updateHour) { setHourClickEnable(it) }
  }

  private fun setUpTimePeriodRecyclerView() {
    val ampmList = generateAmpmList()
    val newAmpmList = ampmList.mapIndexed { index, item ->
      item.copy(isSelected = index == setTimerViewModel.currentAmPmIndex.value)
      }
    timePeriodAdapter = TimePeriodAdapter()
    setupRecyclerView(binding.rvTimePickerAmpm, newAmpmList, timePeriodAdapter, ::updateAmPm) { setPeriodClickEnable(it) }
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
    setTimerViewModel.setSelectedHour(hour)
    setTimerViewModel.currentHourIndex.value = index
  }

  private fun updateMinute(item: PickerItem, index: Int) {
    val minute = item.convertToText()
    setTimerViewModel.setSelectedMinute(minute)
    setTimerViewModel.currentMinuteIndex.value = index
  }

  private fun updateAmPm(item: PickerItem, index: Int) {
    val timePeriod = item.convertToText()
    setTimerViewModel.setSelectedPeriod(timePeriod)
    setTimerViewModel.currentAmPmIndex.value = index
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
      findNavController().navigate(R.id.action_navigation_time_picker_to_navigation_timer)
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

  private fun initCompleteButtonClickListener() {
    binding.btnTimePickerNext.btnClick {
      Log.e("클릭", "클릭")
      if(args.argPatch) setTimerViewModel.patchTimer(args.argTimerId) else setTimerViewModel.postTimer()
    }
  }

  private fun collectPostTimerState() {
    setTimerViewModel.postTimerState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          timerViewModel.getTimerMain()
          findNavController().apply {
            navigate(R.id.action_navigation_time_picker_to_navigation_timer)
          }
        }
        is UiState.Failure -> {}
        is UiState.Loading -> {}
        is UiState.Empty -> {}
      }
    }.launchIn(viewLifeCycleScope)
  }

  private fun collectSelectedTime() {
    setTimerViewModel.selectedTime.flowWithLifecycle(viewLifeCycle).onEach {
      binding.tvTimePickerTime.text = "${it.timePeriod} ${it.hour}시 ${it.minute}분"
    }.launchIn(viewLifeCycleScope)
  }
}
