package org.sopt.mainfeature.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {
  lateinit var binding : FragmentTimerBinding
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    binding = FragmentTimerBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.clTimerPermission.setOnClickListener {
      if(binding.clTimerPermissionOff.isVisible){
        binding.clTimerPermissionOff.isGone = true
      }else{
        binding.clTimerPermissionOff.isGone = false
      }
    }
  }
}
