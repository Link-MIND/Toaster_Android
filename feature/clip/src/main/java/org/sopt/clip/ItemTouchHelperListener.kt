package org.sopt.clip

interface ItemTouchHelperListener {
  fun onItemMove(from: Int, to: Int)
  fun onItemSwipe(position: Int)
}
