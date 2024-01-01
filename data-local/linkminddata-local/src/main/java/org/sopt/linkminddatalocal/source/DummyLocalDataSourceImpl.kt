package org.sopt.linkminddatalocal.source

import org.sopt.linkminddata.model.local.Dummy
import org.sopt.linkminddata.source.local.DummyLocalDataSource
import javax.inject.Inject

class DummyLocalDataSourceImpl @Inject constructor() : DummyLocalDataSource {
  override suspend fun getDummy(): Dummy = Dummy("더미")
}
