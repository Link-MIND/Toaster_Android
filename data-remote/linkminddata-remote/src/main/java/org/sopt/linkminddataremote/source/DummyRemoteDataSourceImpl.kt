package org.sopt.linkminddataremote.source

import org.sopt.linkminddata.model.remote.response.ResponseGetDummyDto
import org.sopt.linkminddata.source.remote.DummyRemoteDataSource
import org.sopt.linkminddata.sourceimpl.api.ExampleService
import javax.inject.Inject

class DummyRemoteDataSourceImpl @Inject constructor(
  private val exampleService: ExampleService,
) : DummyRemoteDataSource {
  override suspend fun getDummy(): ResponseGetDummyDto = exampleService.getDummy()
}
