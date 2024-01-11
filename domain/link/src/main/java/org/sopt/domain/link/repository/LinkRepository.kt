package org.sopt.domain.link.repository

interface LinkRepository {
  suspend fun postSaveLink(): Result<Int>
  suspend fun deleteLink(): Result<Int>
  suspend fun patchReadLink(): Result<Int>
}
