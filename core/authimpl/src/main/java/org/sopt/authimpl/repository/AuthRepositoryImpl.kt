package org.sopt.authimpl.repository

import org.sopt.auth.model.Auth
import org.sopt.auth.model.Token
import org.sopt.auth.model.UserData
import org.sopt.auth.repository.AuthRepository
import org.sopt.authimpl.model.request.RequestPostAuthDto.Companion.toDataModel
import org.sopt.authimpl.source.local.AuthLocalDataSource
import org.sopt.authimpl.source.remote.AuthRemoteDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val authLocalDataSource: AuthLocalDataSource,
  private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
  override suspend fun authenticate(auth: Auth): Result<Pair<Token, UserData>?> =
    runCatching { authRemoteDataSource.authenticate(auth.socialToken, auth.toDataModel()).data?.toDomainModel() }

  override suspend fun signout(): Result<Unit> =
    runCatching { authRemoteDataSource.signout() }
  override suspend fun withdraw(): Result<Unit> =
    runCatching { authRemoteDataSource.withdraw() }

  override suspend fun saveToken(token: Token) =
    authLocalDataSource.save(token)

  override suspend fun clearToken() =
    authLocalDataSource.clear()

}
