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
import designsystem.components.dialog.LinkMindDialog
import designsystem.components.toast.linkMindSnackBar
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
    setupForPatch()
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

  private fun setupForPatch() {
    if (args.argPatch && setTimerViewModel.isFirst.value) {
      setTimerViewModel.isFirst.value = false
      setupTime()
      setupRepeatDays()
    }
  }

  private fun setupTime() {
    val splitString = args.argRemindTime.replace("시", "").replace("분", "").split(" ")
    val period = splitString[0]
    val hour = splitString[1]
    val minute = if (splitString.size > 2) splitString[2] else null

    setTimerViewModel.apply {
      setSelectedPeriod(period)
      currentAmPmIndex.value = if (period == "오전") 1 else 2
      setSelectedHour(hour)
      currentHourIndex.value = if(hour.toInt() == 12) hour.toInt() - 12 else hour.toInt()
      minute?.let {
        setSelectedMinute(it)
        currentMinuteIndex.value = it.toInt() + 1
      }
    }
  }

  private fun setupRepeatDays() {
    val daysOfWeek = listOf("월", "화", "수", "목", "금", "토", "일")
    val repeatDays = args.argRemindDates.split(", ")
    val repeatDaysIndex = repeatDays.map { day -> daysOfWeek.indexOf(day) }

    repeatDaysIndex.filter { it < 7 }.forEach { setTimerViewModel.setRepeat(it + 3) }
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
    binding.tvTimePickerCategory.text = "${if (args.argPatch) args.argCategoryName else getClipName()} 클립을"
  }

  private fun getClipName(): String {
    val clipStateData = (setTimerViewModel.clipState.value as UiState.Success).data
    val selectedClip = clipStateData.first { it.isSelected }
    return if (selectedClip.name == "전체 클립") "전체" else selectedClip.name
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
        text = modified + " 마다"
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
      val linkMindDialog = LinkMindDialog(requireContext())
      linkMindDialog.setTitle(org.sopt.mainfeature.R.string.timer_cancel_dialog_title)
      linkMindDialog.setSubtitle(org.sopt.mainfeature.R.string.timer_cancel_dialog_sub_title)
      linkMindDialog.setPositiveButton(org.sopt.mainfeature.R.string.positive_ok_msg) {
        findNavController().navigate(R.id.action_navigation_time_picker_to_navigation_timer)
        linkMindDialog.dismiss()
      }
      linkMindDialog.setNegativeButton(org.sopt.mainfeature.R.string.negative_close_cancel) {
        linkMindDialog.dismiss()
      }
      linkMindDialog.show()
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
      if (args.argPatch) setTimerViewModel.patchTimer(args.argTimerId) else setTimerViewModel.postTimer()
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

        is UiState.Failure -> {
          if (state.msg.contains("422")) {
            Log.e("로그", state.msg)
            requireContext().linkMindSnackBar(binding.btnTimePickerNext, "한 클립당 하나의 타이머만 설정 가능해요", true)
          } else if (state.msg.contains("400")) {
            requireContext().linkMindSnackBar(binding.btnTimePickerNext, "최대 5개의 타이머만 설정 가능해요", true)
          }
        }

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
