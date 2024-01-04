package org.sopt.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import org.sopt.ui.context.hideKeyboard

abstract class BindingActivity<T : ViewBinding>(
  private val inflater: (LayoutInflater) -> T,
) : AppCompatActivity() {
  protected lateinit var binding: T

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = inflater(layoutInflater)
    setContentView(binding.root)
  }

  override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
    hideKeyboard(currentFocus ?: View(this))
    return super.dispatchTouchEvent(ev)
  }
}
