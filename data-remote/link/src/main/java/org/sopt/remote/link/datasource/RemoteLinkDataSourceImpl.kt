package org.sopt.remote.link.datasource

import org.sopt.data.link.datasource.RemoteLinkDataSource
import org.sopt.remote.link.api.LinkService
import org.sopt.remote.link.request.RequestIsReadDto
import org.sopt.remote.link.request.RequestPatchTitleDto
import org.sopt.remote.link.request.RequestWriteDto
import javax.inject.Inject

class RemoteLinkDataSourceImpl @Inject constructor(
  private val linkService: LinkService,
) : RemoteLinkDataSource {
  override suspend fun postSaveLink(linkUrl: String, categoryId: Long?): Int =
    linkService.postLink(
      RequestWriteDto(
        linkUrl = linkUrl,
        categoryId = categoryId,
      ),
    ).code

  override suspend fun deleteLink(toastId: Long): Int {
    return linkService.deleteLink(toastId).code
  }

  override suspend fun patchReadLink(toastId: Long, isRead: Boolean): Boolean =
    linkService.patchLink(
      RequestIsReadDto(
        toastId = toastId,
        isRead = isRead,
      ),
    ).data!!.isRead

  override suspend fun patchLinkTitle(toastId: Long, title: String): String =
    linkService.patchLinkTitle(
      RequestPatchTitleDto(
        toastId = toastId,
        title = title,
      ),
    ).data!!.updatedTitle
}
