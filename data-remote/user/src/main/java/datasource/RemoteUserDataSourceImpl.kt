package datasource

import api.UserService
import org.sopt.model.user.MyPageData
import org.sopt.model.user.SettingPageData
import org.sopt.user.source.RemoteUserDataSource
import request.RequestIsAllowedDto
import response.toCoreModel
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
  private val userService: UserService,
) : RemoteUserDataSource {
  override suspend fun getMyPage(): MyPageData = userService.getMyPage().data!!.toCoreModel()

  override suspend fun getSetting(): SettingPageData = userService.getSetting().data!!.toCoreModel()

  override suspend fun patchAllowedPush(allowedPush: Boolean): Boolean = userService.patchPush(
    RequestIsAllowedDto(
      allowedPush = allowedPush,
    ),
  ).data!!.isFcmAllowed
}
