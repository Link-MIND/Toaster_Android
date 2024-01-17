package org.sopt.data.category.datasource

import org.sopt.model.category.SearchResultList

interface RemoteSearchDataSource {
  suspend fun getSearchResult(query: String): SearchResultList
}
