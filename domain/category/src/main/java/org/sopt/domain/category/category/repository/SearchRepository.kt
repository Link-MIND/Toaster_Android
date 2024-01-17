package org.sopt.domain.category.category.repository

import org.sopt.model.category.SearchResultList

interface SearchRepository {
  suspend fun getSearchResult(query: String): Result<SearchResultList>
}
