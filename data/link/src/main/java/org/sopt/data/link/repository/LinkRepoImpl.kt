package org.sopt.data.link.repository

import org.sopt.data.link.datasource.RemoteLinkDataSource
import org.sopt.domain.link.repository.LinkRepository
import org.sopt.model.category.CategoryList
import javax.inject.Inject

class LinkRepoImpl @Inject constructor(
  private val remoteCategoryDataSource: RemoteLinkDataSource,
) : LinkRepository {
  override suspend fun postSaveLink(): Result<Int> =
  runCatching { remoteCategoryDataSource.postSaveLink() }

  override suspend fun deleteLink(): Result<Int> =
    runCatching { remoteCategoryDataSource.deleteLink() }

  override suspend fun patchReadLink(): Result<Int> =
    runCatching { remoteCategoryDataSource.patchReadLink() }
}
