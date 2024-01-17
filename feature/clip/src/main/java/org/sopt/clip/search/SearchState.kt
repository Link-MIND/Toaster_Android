package org.sopt.clip.search

sealed interface SearchState<out T> {
  data class Success<T>(
    val data: T,
  ) : SearchState<T>
  object NoResult : SearchState<Nothing>

  data class Failure(
    val error: String
  ) : SearchState<Nothing>

  object Loading : SearchState<Nothing>

  object Empty : SearchState<Nothing>
}
