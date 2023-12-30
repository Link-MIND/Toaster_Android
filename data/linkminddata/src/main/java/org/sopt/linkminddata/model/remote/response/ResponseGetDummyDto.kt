package org.sopt.linkminddata.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.linkminddomain.entity.DummyEntity

@Serializable
data class ResponseGetDummyDto(
  @SerialName("dummy")
  val response: String,
) {
  fun toDomainModel(): DummyEntity = DummyEntity(this.response)
}
