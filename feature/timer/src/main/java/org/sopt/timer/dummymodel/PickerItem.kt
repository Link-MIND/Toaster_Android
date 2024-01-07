package org.sopt.timer.dummymodel

data class PickerItem(
  val text: String,
  val isSelected: Boolean,
) {
  fun convertToText() = this.text.toIntOrNull()?.let {
    if (it < 10) "0${this.text}" else this.text
  } ?: this.text
}
