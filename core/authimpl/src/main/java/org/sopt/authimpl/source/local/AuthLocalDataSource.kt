package org.sopt.authimpl.source.local

import org.sopt.auth.model.Token

interface AuthLocalDataSource {
  suspend fun save(token: Token)

  suspend fun getToken(): Token
  suspend fun clear()
}
