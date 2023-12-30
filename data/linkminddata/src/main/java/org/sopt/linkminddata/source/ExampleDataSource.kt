package org.sopt.linkminddata.source

import org.sopt.linkminddata.model.remote.response.ResponseGetDummyDto

interface ExampleDataSource {
  suspend fun getDummy(): ResponseGetDummyDto
}
