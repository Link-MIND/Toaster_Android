package org.sopt.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.home.usecase.GetMainPageUserClip
import org.sopt.home.usecase.GetRecommendSite
import org.sopt.home.usecase.GetWeekBestLink
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val getMainPageUserClip: GetMainPageUserClip,
  private val getRecommendSite: GetRecommendSite,
  private val getWeekBestLink: GetWeekBestLink,
) : ViewModel() {
  init {
    getMainPageUserClip()
    getRecommendSite()
    getWeekBestLink()
  }

  fun getMainPageUserClip() = viewModelScope.launch {
    getMainPageUserClip.invoke().onSuccess {
      Log.d("MainUserSuccess", "$it")
    }.onFailure {
      Log.d("MainUser", "$it")
    }
  }

  fun getRecommendSite() = viewModelScope.launch {
    getRecommendSite.invoke().onSuccess {
      Log.d("RecommendSuccess", "$it")
    }.onFailure {
      Log.d("Recommend", "$it")
    }
  }

  fun getWeekBestLink() = viewModelScope.launch {
    getWeekBestLink.invoke().onSuccess {
      Log.d("getWeekBestLinkSuccess", "$it")
    }.onFailure {
      Log.d("getWeekBestLink", "$it")
    }
  }
}
