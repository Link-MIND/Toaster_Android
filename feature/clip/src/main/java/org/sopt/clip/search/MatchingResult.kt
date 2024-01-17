package org.sopt.clip.search

data class MatchingResult(
  val linkResults: List<LinkResultDummy>,
  val clipResults: List<ClipResultDummy>,
  val matchingTitles: List<String>,
)
