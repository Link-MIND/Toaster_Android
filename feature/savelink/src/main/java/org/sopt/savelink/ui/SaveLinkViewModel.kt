package org.sopt.savelink.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.domain.link.usecase.DeleteLinkUseCase
import org.sopt.domain.link.usecase.PatchReadLinkUseCase
import javax.inject.Inject

@HiltViewModel
class SaveLinkViewModel @Inject constructor(
  private val deleteLinkUseCase: DeleteLinkUseCase,
  private val patchReadLinkUseCase: PatchReadLinkUseCase,
) : ViewModel() {
  fun deleteLink(toastId: Long) = viewModelScope.launch {
    deleteLinkUseCase(
      DeleteLinkUseCase.Param(
        toastId = toastId,
      ),
    ).onSuccess {

    }.onFailure { }
  }

  fun patchReadLink(toastId: Long) = viewModelScope.launch {
    patchReadLinkUseCase(
      PatchReadLinkUseCase.Param(
        toastId = toastId,
      ),
    ).onSuccess { }.onFailure { }
  }
}
