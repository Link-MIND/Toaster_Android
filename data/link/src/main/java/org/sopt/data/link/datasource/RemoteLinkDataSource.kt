package org.sopt.data.link.datasource

interface RemoteLinkDataSource {
  suspend fun postSaveLink(): Int
  suspend fun deleteLink(): Int
  suspend fun patchReadLink(): Int
}
