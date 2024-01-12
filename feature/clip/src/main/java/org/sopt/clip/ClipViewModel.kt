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
