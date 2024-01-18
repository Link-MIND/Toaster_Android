package org.sopt.data.link.datasource

interface RemoteLinkDataSource {
  suspend fun postSaveLink(linkUrl: String, categoryId: Long?): Int
  suspend fun deleteLink(toastId: Long): Int
  suspend fun patchReadLink(toastId: Long): Boolean
}
