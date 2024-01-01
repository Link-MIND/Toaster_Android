package org.sopt.ui.view

import android.view.View

fun View.onThrottleClick(
  onClick: (v: View) -> Unit,
) {
  val listener = View.OnClickListener { onClick(it) }
  setOnClickListener(OnThrottleClickListener(listener))
}
class OnThrottleClickListener(
  private val clickListener: View.OnClickListener,
  private val interval: Long = 300L,
) : View.OnClickListener {

  private var clicked = false
  override fun onClick(v: View?) {
    if (!clicked) {
      clicked = true
      v?.run {
        postDelayed(
          { clicked = false },
          interval,
        )
        clickListener.onClick(v)
      }
    }
  }
}
