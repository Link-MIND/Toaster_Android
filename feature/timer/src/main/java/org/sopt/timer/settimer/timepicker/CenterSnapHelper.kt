package org.sopt.timer.settimer.timepicker

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

class CenterSnapHelper : LinearSnapHelper() {

  override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray? {
    val out = IntArray(2)
    if (layoutManager.canScrollHorizontally()) {
      out[0] = distanceToCenter(layoutManager, targetView, OrientationHelper.createHorizontalHelper(layoutManager))
    } else {
      out[0] = 0
    }

    if (layoutManager.canScrollVertically()) {
      out[1] = distanceToCenter(layoutManager, targetView, OrientationHelper.createVerticalHelper(layoutManager))
    } else {
      out[1] = 0
    }
    return out
  }

  private fun distanceToCenter(layoutManager: RecyclerView.LayoutManager, targetView: View, helper: OrientationHelper): Int {
    val childCenter = helper.getDecoratedStart(targetView) + helper.getDecoratedMeasurement(targetView) / 2
    val containerCenter: Int
    containerCenter = if (layoutManager.clipToPadding) {
      helper.startAfterPadding + helper.totalSpace / 2
    } else {
      helper.end / 2
    }
    return childCenter - containerCenter
  }
}
