package org.sopt.data.link.datasource

interface RemoteLinkDataSource {
  suspend fun postSaveLink(): Int
  suspend fun deleteLink(toastId: Long): Int
  suspend fun patchReadLink(toastId: Long): Int
}
