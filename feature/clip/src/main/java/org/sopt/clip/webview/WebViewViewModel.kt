package org.sopt.clip.webview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.sopt.domain.link.usecase.PatchReadLinkUseCase
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
  private val patchReadLinkUseCase: PatchReadLinkUseCase,
) : ViewModel() {
  val patchReadLinkResult = MutableStateFlow(false)
  fun patchReadLink(toastId: Long, isRead: Boolean) = viewModelScope.launch {
    patchReadLinkUseCase(param = PatchReadLinkUseCase.Param(toastId, isRead)).onSuccess {
      patchReadLinkResult.emit(it)
    }.onFailure {
      Log.e("실패", it.message.toString())
    }
  }
}
