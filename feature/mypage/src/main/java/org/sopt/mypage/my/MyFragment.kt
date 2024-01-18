package org.sopt.mypage.my

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.model.user.MyPageData
import org.sopt.mypage.R
import org.sopt.mypage.databinding.FragmentMyBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.fragment.viewLifeCycle
import org.sopt.ui.fragment.viewLifeCycleScope
import org.sopt.ui.view.UiState

@AndroidEntryPoint
class MyFragment : BindingFragment<FragmentMyBinding>({ FragmentMyBinding.inflate(it) }) {

  private val viewModel by viewModels<MyPageViewModel>()
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.getUserMyPage()
    viewModel.myPageState.flowWithLifecycle(viewLifeCycle).onEach { state ->
      when (state) {
        is UiState.Success -> {
          initMyPageData(state.data)
        }
        else -> {}
      }
    }.launchIn(viewLifeCycleScope)
    setOnClickListener()
  }

  private fun setOnClickListener() {
    binding.ivSetting.setOnClickListener {
      findNavController().navigate(R.id.action_navigation_my_to_navigation_setting)
    }
  }

  private fun initMyPageData(data: MyPageData) {
    val myPage = data
    if (myPage != null) {
      with(binding) {
        tvUserName.text = myPage.nickname
        tvMyTotalLinkNum.text = myPage.allReadToast.toString()
        tvReadLinkThisWeekNum.text = myPage.thisWeekendRead.toString()
        tvSaveLinkThisWeekNum.text = myPage.thisWeekendSaved.toString()
        ivProfile.load(data.profile) { transformations(CircleCropTransformation()) }
      }
    }
  }
}
