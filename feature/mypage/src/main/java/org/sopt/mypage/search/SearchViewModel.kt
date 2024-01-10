package org.sopt.mypage.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

  private val _linkResultsLiveData = MutableLiveData<List<LinkResultDummy>>()
  val linkResultsLiveData: LiveData<List<LinkResultDummy>> get() = _linkResultsLiveData

  private val _clipResultsLiveData = MutableLiveData<List<ClipResultDummy>>()
  val clipResultsLiveData: LiveData<List<ClipResultDummy>> get() = _clipResultsLiveData


  fun updateResults(linkResults: List<LinkResultDummy>, clipResults: List<ClipResultDummy>) {
    _linkResultsLiveData.value = linkResults
    _clipResultsLiveData.value = clipResults
  }

  fun onClickSearch(query: String): Boolean {
    val filteredLinkResults = linkResultsLiveData.value.orEmpty().filter { it.title.contains(query, ignoreCase = true) }
    val filteredClipResults = clipResultsLiveData.value.orEmpty().filter { it.title.contains(query, ignoreCase = true) }

    updateResults(filteredLinkResults, filteredClipResults)
    return filteredLinkResults.isNotEmpty() || filteredClipResults.isNotEmpty()

  }
}
