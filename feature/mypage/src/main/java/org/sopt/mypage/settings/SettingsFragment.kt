package org.sopt.mypage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
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
import org.sopt.model.user.SettingPageData
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

    viewModel.settingState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          initSettingPageData(state.data)
        }

        else -> {}
      }
    }.launchIn(viewLifeCycleScope)

    viewModel.getUserInfo()

    viewModel.pushIsAllowed.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        true -> {
          binding.tvSettingsAlertOff.isGone = true
        }

        false -> {
          binding.tvSettingsAlertOff.isVisible = true
        }
      }
    }.launchIn(viewLifeCycleScope)

    onClickToggle()
    onClickLogoutBtn()
    onClickCloseBtn()
    onClickLeftBtn()
    onClickWithdrawBtn()
  }

  private fun onClickCloseBtn() {
    binding.ivSettingsClose.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun onClickLeftBtn() {
    binding.ivSettingsLeft.onThrottleClick {
      findNavController().navigateUp()
    }
  }

  private fun onClickLogoutBtn() {
    binding.tvSetLogout.onThrottleClick {
      viewModel.logout()
    }
  }

  private fun onClickToggle() {
    binding.tgPushAllowed.onThrottleClick {
      viewModel.patchPush(!viewModel.pushIsAllowed.value)
      binding.settingsAlertToggle.transition()
    }
  }

  private fun onClickWithdrawBtn() {
    binding.tvWithdraw.setOnClickListener {
      viewModel.withdraw()
    }
  }

  private fun initSettingPageData(data: SettingPageData) {
    with(binding) {
      tvUserName.text = data.nickname
      if (data.fcmIsAllowed) {
        tvSettingsAlertOff.isGone = true
        settingsAlertToggle.initToggleState(true)
      } else {
        tvSettingsAlertOff.isVisible = true
        settingsAlertToggle.initToggleState(false)
      }
    }
  }
}
