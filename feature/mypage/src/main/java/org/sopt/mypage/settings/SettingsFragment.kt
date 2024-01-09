package org.sopt.mypage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.common.intentprovider.IntentProvider
import org.sopt.common.intentprovider.LOGIN
import org.sopt.datastore.datastore.SecurityDataStore
import org.sopt.mypage.databinding.FragmentSettingsBinding
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope
import org.sopt.ui.view.UiState
import org.sopt.ui.view.onThrottleClick
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {
  private lateinit var binding: FragmentSettingsBinding
  private val viewModel: SettingsViewModel by viewModels()

  @Inject
  @LOGIN
  lateinit var intentProvider: IntentProvider

  @Inject
  lateinit var dataStore: SecurityDataStore
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    binding = FragmentSettingsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)


    val toasterToggle = binding.settingsAlertToggle
    val tvSettingsAlertOff = binding.tvSettingsAlertOff

    val startStateId = org.sopt.mainfeature.R.id.start

    updateVisibility(tvSettingsAlertOff, toasterToggle.getState(), startStateId)

    toasterToggle.btnClick {
      updateVisibility(tvSettingsAlertOff, toasterToggle.getState(), startStateId)
    }

    binding.tvSetLogout.setOnClickListener {
      viewModel.logout()
    }

    viewModel.logoutState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          dataStore.setAutoLogin(false)
          val intent = intentProvider.getIntent()
          startActivity(intent)
          requireActivity().finish()
        }

        else -> {}
      }
    }.launchIn(viewLifeCycleScope)

    binding.tvWithdraw.setOnClickListener {
      viewModel.withdraw()
    }

    viewModel.withdrawState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          dataStore.setAutoLogin(false)
          val intent = intentProvider.getIntent()
          startActivity(intent)
          requireActivity().finish()
        }

        else -> {}
      }
    }.launchIn(viewLifeCycleScope)

    binding.ivSettingsLeft.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun updateVisibility(view: View, state: Int, startStateId: Int) {
    view.visibility = if (state == startStateId) View.VISIBLE else View.INVISIBLE
  }
}
