package org.sopt.clip.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.domain.category.category.usecase.GetSearchResultUserCase
import org.sopt.model.category.SearchResultList
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val getSearchResultUserCase: GetSearchResultUserCase,
) : ViewModel() {
  private val _searchState = MutableStateFlow<SearchState<SearchResultList>>(SearchState.Empty)
  val searchState: StateFlow<SearchState<SearchResultList>> = _searchState.asStateFlow()
  fun getSearchResult(query: String) = viewModelScope.launch {
    _searchState.emit(SearchState.Loading)
    getSearchResultUserCase(query).onSuccess {
      if (it.categories.isNullOrEmpty() && it.toasts.isNullOrEmpty()) {
        _searchState.emit(SearchState.NoResult)
      } else {
        _searchState.emit(SearchState.Success(it))
      }
    }.onFailure {
      Log.e("에러", it.message.toString())
      _searchState.emit(SearchState.Failure(it.message.toString()))
    }
  }
}
