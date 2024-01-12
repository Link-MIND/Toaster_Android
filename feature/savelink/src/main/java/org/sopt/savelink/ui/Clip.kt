package org.sopt.savelink.ui

data class Clip(
  val name: String,
  val count: Int,
  var isSelected: Boolean,
) {
  fun selectNewClip() {
    isSelected = true
  }
  fun toggleSelection() {
    isSelected = !isSelected
  }
}
