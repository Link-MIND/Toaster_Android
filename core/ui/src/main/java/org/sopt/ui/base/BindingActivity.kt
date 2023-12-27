package org.sopt.ui.base

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.sopt.ui.context.hideKeyboard

abstract class BindingActivity<T : ViewDataBinding>(
  @LayoutRes private val layoutRes: Int,
) : AppCompatActivity() {
  protected lateinit var binding: T

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, layoutRes)
    binding.lifecycleOwner = this
  }

  override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
    hideKeyboard(currentFocus ?: View(this))
    return super.dispatchTouchEvent(ev)
  }
}
