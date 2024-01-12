package org.sopt.domain.link.repository

interface LinkRepository {
  suspend fun postSaveLink(): Result<Int>
  suspend fun deleteLink(toastId: Long): Result<Int>
  suspend fun patchReadLink(toastId: Long): Result<Int>
}
