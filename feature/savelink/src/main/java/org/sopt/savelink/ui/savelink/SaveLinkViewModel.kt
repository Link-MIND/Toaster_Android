package org.sopt.savelink.ui.savelink

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SaveLinkViewModel @Inject constructor() : ContainerHost<LinkState, LinkSideEffect>, ViewModel() {
  override val container: Container<LinkState, LinkSideEffect> =
    container(LinkState())

  fun updateEditText(text: String) = intent {
    reduce {
      state.copy(edittextLink = text)
    }
  }

  fun navigateUp() = intent { postSideEffect(LinkSideEffect.NavigateUp) }
  fun navigateSetLink() = intent { postSideEffect(LinkSideEffect.NavigateSetLink) }
  fun showDialog() = intent { postSideEffect(LinkSideEffect.ShowDialog) }
}
