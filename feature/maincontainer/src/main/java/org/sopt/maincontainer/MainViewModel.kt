package org.sopt.maincontainer

import android.util.Log
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
class MainViewModel @Inject constructor() : ContainerHost<MainState, MainSideEffect>, ViewModel() {
  override val container: Container<MainState, MainSideEffect> =
    container(MainState())

  fun updateClipBoard(clipboard: String) = intent {
    reduce {
      Log.d("test", "$clipboard")
      state.copy(clipboard = clipboard)
    }
  }

  fun updateBnvVisible(isCheck: Boolean) = intent {
    reduce {
      state.copy(isBottomNavigationBarVisible = isCheck)
    }
  }

  fun navigateSaveLink() = intent {
    postSideEffect(MainSideEffect.NavigateSaveLink)
  }
}
