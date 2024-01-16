package org.sopt.savelink.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.category.category.usecase.GetCategoryAllUseCase
import org.sopt.domain.category.category.usecase.PostAddCategoryTitleUseCase
import org.sopt.domain.link.usecase.PostSaveLinkUseCase
import javax.inject.Inject

@HiltViewModel
class SaveLinkViewModel @Inject constructor(
) : ContainerHost<LinkState, LinkSideEffect>, ViewModel() {
  override val container: Container<LinkState, LinkSideEffect> =
    container(LinkState())

  fun navigateUp() = intent { postSideEffect(LinkSideEffect.NavigateUp) }
  fun navigateSetLink() = intent { postSideEffect(LinkSideEffect.NavigateSetLink) }
  fun showBottomSheet() = intent { postSideEffect(LinkSideEffect.ShowBottomSheet) }
}
