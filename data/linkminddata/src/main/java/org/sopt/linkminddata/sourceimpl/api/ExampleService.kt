package org.sopt.linkminddata_remote.source.api

import org.sopt.linkminddata.model.remote.response.ResponseGetDummyDto
import retrofit2.http.GET

interface ExampleService {
  @GET("dummy")
  suspend fun getDummy() : ResponseGetDummyDto
}
