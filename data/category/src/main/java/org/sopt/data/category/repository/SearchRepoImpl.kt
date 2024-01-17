package org.sopt.data.category.repository

import org.sopt.data.category.datasource.RemoteSearchDataSource
import org.sopt.domain.category.category.repository.SearchRepository
import org.sopt.model.category.SearchResultList
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(
  private val remoteSearchDataSource: RemoteSearchDataSource,
) : SearchRepository {
  override suspend fun getSearchResult(query: String): Result<SearchResultList> =
    runCatching { remoteSearchDataSource.getSearchResult(query) }
}
