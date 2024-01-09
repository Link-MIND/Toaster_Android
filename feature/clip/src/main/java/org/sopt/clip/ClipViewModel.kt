package org.sopt.clip

import androidx.lifecycle.ViewModel

class ClipViewModel : ViewModel() {
  val mockClipData = listOf<ClipsDTO>(
    ClipsDTO("a", 1, 1),
    ClipsDTO("b", 2, 2),
    ClipsDTO("c", 3, 3),
    ClipsDTO("a", 1, 1),
    ClipsDTO("b", 2, 2),
    ClipsDTO("c", 3, 3),
    ClipsDTO("a", 1, 1),
    ClipsDTO("b", 2, 2),
    ClipsDTO("c", 3, 3),
    ClipsDTO("a", 1, 1),
    ClipsDTO("b", 2, 2),
    ClipsDTO("c", 3, 3),
    )

  val mockLinkData = listOf<LinkDTO>(
    LinkDTO("www.l.com", 12, "제목1", "맛집"),
    LinkDTO("www.i.com", 12, "제목2", "맛집2"),
    LinkDTO("www.n.com", 12, "제목3", "맛집3"),
    LinkDTO("www.k.com", 12, "제목4", "맛집4"),
    LinkDTO("www.l.com", 12, "제목1", "맛집"),
    LinkDTO("www.i.com", 12, "제목2", "맛집2"),
    LinkDTO("www.n.com", 12, "제목3", "맛집3"),
    LinkDTO("www.k.com", 12, "제목4", "맛집4"),
    LinkDTO("www.l.com", 12, "제목1", "맛집"),
    LinkDTO("www.i.com", 12, "제목2", "맛집2"),
    LinkDTO("www.n.com", 12, "제목3", "맛집3"),
    LinkDTO("www.k.com", 12, "제목4", "맛집4"),
    )
}
