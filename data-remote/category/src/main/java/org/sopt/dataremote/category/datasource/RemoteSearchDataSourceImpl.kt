package org.sopt.dataremote.category.datasource

import org.sopt.data.category.datasource.RemoteSearchDataSource
import org.sopt.dataremote.category.api.SearchService
import org.sopt.model.category.SearchResultList
import javax.inject.Inject

class RemoteSearchDataSourceImpl @Inject constructor(
  private val searchService: SearchService,
) : RemoteSearchDataSource {
  override suspend fun getSearchResult(query: String): SearchResultList {
    val response = searchService.getSearchResult(query)
    return SearchResultList(
      toasts = response.data?.toasts,
      categories = response.data?.categories,
      keyword = response.data?.keyword,
    )
  }
}
