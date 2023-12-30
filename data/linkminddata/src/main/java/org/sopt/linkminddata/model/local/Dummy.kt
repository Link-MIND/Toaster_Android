package org.sopt.linkminddata.model.local

import org.sopt.linkminddomain.entity.DummyEntity

data class Dummy(
  val string: String,
) {
  fun toDomainModel(): DummyEntity = DummyEntity(this.string)
}
