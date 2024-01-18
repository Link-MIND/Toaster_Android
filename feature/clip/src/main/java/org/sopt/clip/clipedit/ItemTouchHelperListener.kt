package org.sopt.clip.clipedit

interface ItemTouchHelperListener {
  fun onItemMove(from: Int, to: Int)
  fun onItemSwipe(position: Int)
}
