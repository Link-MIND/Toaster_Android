package org.sopt.mainfeature

//@HiltViewModel
//class ExampleViewModel @Inject constructor(
//  private val dummyUseCase: DummyUseCase,
//) : ViewModel() {
//  private val _dummyState = MutableStateFlow<UiState<DummyEntity>>(UiState.Empty)
//  val dummyState: StateFlow<UiState<DummyEntity>> = _dummyState.asStateFlow()
//
//  fun getDummy() = viewModelScope.launch {
//    dummyUseCase().onSuccess {
//      _dummyState.emit(UiState.Success(it))
//    }.onFailure {
//      _dummyState.emit(UiState.Failure(it.message.toString()))
//    }
//  }
//}
