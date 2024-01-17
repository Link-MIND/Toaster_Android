package org.sopt.domain.category.category.usecase

import org.sopt.domain.category.category.repository.SearchRepository
import org.sopt.model.category.SearchResultList
import javax.inject.Inject

class GetSearchResultUserCase @Inject constructor(
  private val searchRepository: SearchRepository,
) {
  suspend operator fun invoke(query: String): Result<SearchResultList> = searchRepository.getSearchResult(
    query = query,
  )
}
