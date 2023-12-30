package org.sopt.linkminddata_remote.source

import org.sopt.linkminddata.model.remote.response.ResponseGetDummyDto
import org.sopt.linkminddata.source.ExampleDataSource
import org.sopt.linkminddata.source.remote.DummyRemoteDataSource
import org.sopt.linkminddata_remote.source.api.ExampleService
import javax.inject.Inject

class ExampleDataSourceImpl @Inject constructor(
  private val exampleService: ExampleService
) : ExampleDataSource {
  override suspend fun getDummy(): ResponseGetDummyDto = exampleService.getDummy()
}
