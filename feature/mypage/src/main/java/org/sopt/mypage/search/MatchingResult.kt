package org.sopt.mypage.search

data class MatchingResult(
  val linkResults: List<LinkResultDummy>,
  val clipResults: List<ClipResultDummy>,
  val matchingTitles: List<String>,
)
