package org.sopt.mainfeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.linkminddomain.entity.DummyEntity
import org.sopt.linkminddomain.usecase.DummyUseCase
import org.sopt.ui.view.UiState
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
  private val dummyUseCase: DummyUseCase,
) : ViewModel() {
  private val _dummyState = MutableStateFlow<UiState<DummyEntity>>(UiState.Empty)
  val dummyState: StateFlow<UiState<DummyEntity>> = _dummyState.asStateFlow()

  fun getDummy() = viewModelScope.launch {
    dummyUseCase().onSuccess {
      _dummyState.emit(UiState.Success(it))
    }.onFailure {
      _dummyState.emit(UiState.Failure(it.message.toString()))
    }
  }
}
