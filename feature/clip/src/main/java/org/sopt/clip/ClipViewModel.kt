package org.sopt.clip

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.domain.category.category.usecase.GetCategoryAllUseCase
import javax.inject.Inject

@HiltViewModel
class ClipViewModel @Inject constructor(
  private val getCategoryAll: GetCategoryAllUseCase,
) : ViewModel() {

  init {
    getCategoryAll()
  }

  var toggleSelectedPast: SelectedToggle = SelectedToggle.ALL

  val mockClipData = listOf<ClipsDTO>(
    ClipsDTO("a", 1, 1),
    ClipsDTO("b", 2, 2),
    ClipsDTO("c", 3, 3),
    ClipsDTO("a", 1, 4),
    ClipsDTO("b", 2, 5),
    ClipsDTO("c", 3, 6),
    ClipsDTO("a", 1, 7),
    ClipsDTO("b", 2, 8),
    ClipsDTO("c", 3, 9),
    ClipsDTO("a", 1, 10),
    ClipsDTO("b", 2, 11),
    ClipsDTO("c", 3, 12),
    ClipsDTO("a", 1, 13),
    ClipsDTO("b", 2, 14),
    ClipsDTO("c", 3, 15),
    ClipsDTO("a", 1, 16),
    ClipsDTO("b", 2, 17),
    ClipsDTO("c", 3, 18),
    ClipsDTO("a", 1, 20),
    ClipsDTO("b", 2, 21),
    ClipsDTO("c", 3, 22),
    ClipsDTO("a", 1, 23),
    ClipsDTO("b", 2, 24),
    ClipsDTO("c", 3, 25),
  )

  val mockLinkData = listOf<LinkDTO>(
    LinkDTO("www.l.com", 12, "제목1", "맛집", 1),
    LinkDTO("www.i.com", 12, "제목2", "맛집2", 2),
    LinkDTO("www.n.com", 12, "제목3", "맛집3", 3),
    LinkDTO("www.k.com", 12, "제목4", "맛집4", 4),
    LinkDTO("www.l.com", 12, "제목1", "맛집", 5),
    LinkDTO("www.i.com", 12, "제목2", "맛집2", 6),
    LinkDTO("www.n.com", 12, "제목3", "맛집3", 7),
    LinkDTO("www.k.com", 12, "제목4", "맛집4", 8),
    LinkDTO("www.l.com", 12, "제목1", "맛집", 9),
    LinkDTO("www.i.com", 12, "제목2", "맛집2", 10),
    LinkDTO("www.n.com", 12, "제목3", "맛집3", 11),
    LinkDTO("www.k.com", 12, "제목4", "맛집4", 12),
  )

  fun navigateBack(navController: NavController) {
    navController.navigateUp()
  }

  val mockDataListState = MutableLiveData<Boolean>(false)
  fun set(value: Boolean) {
    mockDataListState.value = value
  }

  fun getCategoryAll() = viewModelScope.launch {
    getCategoryAll.invoke().onSuccess {
      Log.d("test", "$it")
    }.onFailure {
    }
  }
}
