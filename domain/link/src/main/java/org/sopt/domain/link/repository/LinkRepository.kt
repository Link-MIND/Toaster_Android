package org.sopt.domain.link.repository

interface LinkRepository {
  suspend fun postSaveLink(linkUrl: String, categoryId: Long?): Result<Int>
  suspend fun deleteLink(toastId: Long): Result<Int>
  suspend fun patchReadLink(toastId: Long, isRead: Boolean): Result<Boolean>
}
