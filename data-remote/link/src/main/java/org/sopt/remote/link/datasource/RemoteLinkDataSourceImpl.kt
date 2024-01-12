package org.sopt.remote.link.datasource

import org.sopt.data.link.datasource.RemoteLinkDataSource
import org.sopt.remote.link.api.LinkService
import org.sopt.remote.link.request.RequestIsReadDto
import javax.inject.Inject

class RemoteLinkDataSourceImpl @Inject constructor(
  private val linkService: LinkService,
) : RemoteLinkDataSource {
  override suspend fun postSaveLink(): Int {
    TODO("Not yet implemented")
  }

  override suspend fun deleteLink(toastId: Long): Int =
    linkService.deleteLink(toastId).code

  override suspend fun patchReadLink(toastId: Long): Int =
    linkService.patchLink(
      RequestIsReadDto(
        toastId = toastId,
        isRead = true,
      ),
    ).code
}
