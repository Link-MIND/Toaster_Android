package org.sopt.timer.settimer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.timer.dummymodel.Clip
import org.sopt.timer.dummymodel.Repeat
import org.sopt.timer.dummymodel.TimePicker

class SetTimerViewModel : ViewModel() {
  private val _clipList = MutableStateFlow<List<Clip>>(emptyList())
  val clipList: StateFlow<List<Clip>> = _clipList.asStateFlow()

  private val _repeatList = MutableStateFlow<List<Repeat>>(emptyList())
  val repeatList: StateFlow<List<Repeat>> = _repeatList.asStateFlow()

  private val _selectedTime = MutableStateFlow(TimePicker("오전", "01", "00"))
  val selectedTime: StateFlow<TimePicker> = _selectedTime.asStateFlow()

  val currentHourIndex = MutableStateFlow(1)
  val currentMinuteIndex = MutableStateFlow(1)
  val currentAmPmIndex = MutableStateFlow(1)

  fun initSetTimer() {
    _clipList.value = listOf(
      Clip("전체 클립", 3, false),
      Clip("전체 클립", 3, false),
      Clip("전체 클립", 3, false),
      Clip("전체 클립", 3, false),
    )
    _repeatList.value = listOf(
      Repeat("매일 (월~일)", false),
      Repeat("주중마다 (월~금)", false),
      Repeat("주말마다 (토~일)", false),
      Repeat("월요일마다", false),
      Repeat("화요일마다", false),
      Repeat("수요일마다", false),
      Repeat("목요일마다", false),
      Repeat("금요일마다", false),
      Repeat("토요일마다", false),
      Repeat("일요일마다", false),
    )
    _selectedTime.value = TimePicker("오전", "01", "00")

    currentHourIndex.value = 1

    currentMinuteIndex.value = 1

    currentAmPmIndex.value = 1
  }

  fun setSelectedHour(hour: String) {
    val timePicker = TimePicker(selectedTime.value.timePeriod, hour, selectedTime.value.minute)
    _selectedTime.value = timePicker
  }

  fun setSelectedMinute(minute: String) {
    val timePicker = TimePicker(selectedTime.value.timePeriod, selectedTime.value.hour, minute)
    _selectedTime.value = timePicker
  }

  fun setSelectedPeriod(period: String) {
    val timePicker = TimePicker(period, selectedTime.value.hour, selectedTime.value.minute)
    _selectedTime.value = timePicker
  }

  fun setClipList(clipList: List<Clip>) {
    _clipList.value = clipList
  }

  fun setRepeatList(repeatList: List<Repeat>) {
    _repeatList.value = repeatList
  }
}
