package org.sopt.auth.repository

import org.sopt.auth.model.Auth
import org.sopt.auth.model.Token
import org.sopt.auth.model.UserData

interface AuthRepository {
  suspend fun authenticate(auth: Auth): Result<Pair<Token, UserData>?>
  suspend fun signout(): Result<Unit>
  suspend fun withdraw(): Result<Unit>
  suspend fun saveToken(token: Token)
  suspend fun clearToken()
}
