package org.sopt.linkminddata.source.local

import org.sopt.linkminddata.model.local.Dummy

interface DummyLocalDataSource {
  suspend fun getDummy() : Dummy
}
