package org.sopt.mainfeature.timer

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.FragmentTimerBinding
import org.sopt.mainfeature.timer.dummymodel.Timer
import org.sopt.ui.fragment.colorOf

class TimerFragment : Fragment() {
  private var _binding: FragmentTimerBinding? = null
  protected val binding
    get() = requireNotNull(_binding) {
    }
  private lateinit var completeTimerAdapter: CompleteTimerAdapter
  private lateinit var waitTimerAdapter: WaitTimerAdapter
  var timerExist = true
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = FragmentTimerBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.tvTimerTitle.setOnClickListener {
      if(timerExist){
        binding.svTimerExist.isVisible = true
        binding.llTimerNotExist.isGone = true
        timerExist = false
      } else {
        binding.svTimerExist.isGone = true
        binding.llTimerNotExist.isVisible = true
        timerExist = true
      }
    }

    completeTimerAdapter = CompleteTimerAdapter({})
    waitTimerAdapter = WaitTimerAdapter({}, {})

    val list = listOf(
      Timer(1, "네이버", "일요일", true, 8, 37),
        Timer(1, "네이버", "일요일", true, 8, 37)
    )

    completeTimerAdapter.submitList(list)
    waitTimerAdapter.submitList(list)
    binding.tvTimerCompleteCount.text = list.count().toString()
    if(list.count() != 0){
      val color = colorOf(R.color.primary)
      val textColor = colorOf(R.color.white)
      val colorStateList = ColorStateList.valueOf(color)
      binding.flTimerCompleteCount.backgroundTintList = colorStateList
      binding.tvTimerCompleteCount.setTextColor(textColor)
    }
    binding.rvTimerComplete.adapter = completeTimerAdapter
    binding.rvTimerWait.adapter = waitTimerAdapter
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}
