package org.sopt.ui.view

import android.content.res.Resources


fun caculateMarignDialog(dpMargin: Float): Int {
  val dpToPixel = Resources.getSystem().displayMetrics.density
  val dialogHorizontalMarginInPixels = (dpToPixel * dpMargin + 0.5f).toInt()
  val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
  return deviceWidth - 2 * dialogHorizontalMarginInPixels
}
