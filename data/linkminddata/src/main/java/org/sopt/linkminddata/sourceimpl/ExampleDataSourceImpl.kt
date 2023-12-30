package org.sopt.linkminddata.sourceimpl

import org.sopt.linkminddata.model.remote.response.ResponseGetDummyDto
import org.sopt.linkminddata.source.ExampleDataSource
import org.sopt.linkminddata.sourceimpl.api.ExampleService
import javax.inject.Inject

class ExampleDataSourceImpl @Inject constructor(
  private val exampleService: ExampleService,
) : ExampleDataSource {
  override suspend fun getDummy(): ResponseGetDummyDto = exampleService.getDummy()
}
