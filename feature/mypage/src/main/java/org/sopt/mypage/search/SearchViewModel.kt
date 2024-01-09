package org.sopt.mypage.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
  val linkResults = MutableLiveData<List<LinkResultDummy>>()
  val clipResults = MutableLiveData<List<ClipResultDummy>>()
  val matchingTitles = MutableLiveData<List<String>>()
  var searchTerm: String = ""

  fun searchResults(searchTerm: String) {
    this.searchTerm = searchTerm
    val matchingResults = findSameResults(searchTerm)

    linkResults.value = matchingResults.linkResults
    clipResults.value = matchingResults.clipResults
    matchingTitles.value = matchingResults.matchingTitles
  }

  private fun findSameResults(searchTerm: String): MatchingResults {
    val matchingTitles = mutableListOf<String>()
    val matchingLinkResults = mutableListOf<LinkResultDummy>()
    val matchingClipResults = mutableListOf<ClipResultDummy>()

    for (linkResult in linkResults.value.orEmpty()) {
      if (linkResult.title.contains(searchTerm, ignoreCase = true)) {
        matchingTitles.add(linkResult.title)
        matchingLinkResults.add(linkResult)
      }
    }

    for (clipResult in clipResults.value.orEmpty()) {
      if (clipResult.title.contains(searchTerm, ignoreCase = true)) {
        matchingTitles.add(clipResult.title)
        matchingClipResults.add(clipResult)
      }
    }

    return MatchingResults(matchingLinkResults, matchingClipResults, matchingTitles)
  }
}
