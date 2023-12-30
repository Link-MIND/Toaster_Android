package org.sopt.linkminddata.source.remote

import org.sopt.linkminddata.model.remote.response.ResponseGetDummyDto

interface DummyRemoteDataSource {
  suspend fun getDummy(): ResponseGetDummyDto
}
