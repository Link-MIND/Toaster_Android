package org.sopt.ui.keyboard

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver

interface OnKeyboardVisibilityListener {
  fun onVisibilityChanged(visible: Boolean)
}

object KeyboardUtils {
  private var keyboardVisibilityListener: ViewTreeObserver.OnGlobalLayoutListener? = null
  fun setKeyboardVisibilityListener(
    parentView: View,
    onKeyboardVisibilityListener: OnKeyboardVisibilityListener
  ) {
    keyboardVisibilityListener = object : ViewTreeObserver.OnGlobalLayoutListener {
      private var alreadyOpen = false
      private val defaultKeyboardHeightDP = 100
      private val estimatedKeyboardDP = defaultKeyboardHeightDP + 48
      private val rect = Rect()

      override fun onGlobalLayout() {
        val estimatedKeyboardHeight = TypedValue.applyDimension(
          TypedValue.COMPLEX_UNIT_DIP,
          estimatedKeyboardDP.toFloat(),
          parentView.resources.displayMetrics
        ).toInt()
        parentView.getWindowVisibleDisplayFrame(rect)
        val heightDiff = parentView.rootView.height - (rect.bottom - rect.top)
        val isShown = heightDiff >= estimatedKeyboardHeight
        if (isShown == alreadyOpen) {
          return
        }
        alreadyOpen = isShown
        onKeyboardVisibilityListener.onVisibilityChanged(isShown)
      }
    }
    parentView.viewTreeObserver.addOnGlobalLayoutListener(keyboardVisibilityListener)
  }

  fun removeKeyboardVisibilityListener(parentView: View) {
    parentView.viewTreeObserver.removeOnGlobalLayoutListener(keyboardVisibilityListener)
    keyboardVisibilityListener = null
  }
}
