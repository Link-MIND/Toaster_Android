package org.sopt.share

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.domain.category.category.usecase.GetCategoryAllUseCase
import org.sopt.domain.link.usecase.PostSaveLinkUseCase
import org.sopt.share.model.Clip
import org.sopt.share.model.toModel
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
  private val saveLinkUseCase: PostSaveLinkUseCase,
  private val getCategoryAllUseCase: GetCategoryAllUseCase,
) : ContainerHost<ShareState, ShareSideEffect>, ViewModel() {
  override val container: Container<ShareState, ShareSideEffect> = container(ShareState())

  init {
    getCategoryAll()
  }
  fun getCategoryAll() = intent {
    getCategoryAllUseCase().onSuccess {
      reduce {
        state.copy(
          allClipCountNum = it.toastNumberInEntire,
          categoryList = (
            listOf(
              Clip(
                0,
                "전체 클립",
                it.toastNumberInEntire,
                true,
              ),
            ) + container.stateFlow.value.categoryList + it.categories.map { it.toModel() }
            ).distinctBy { it.categoryId },
        )
      }
      postSideEffect(ShareSideEffect.ShareActivitySideEffect.DefinedUser)
    }.onFailure {
      postSideEffect(ShareSideEffect.ShareActivitySideEffect.UnDefinedUser)
    }
  }

  private val mutex = Mutex()

  fun saveLink() = intent {
    if (!mutex.isLocked) {
      mutex.withLock {
        saveLinkUseCase(
          PostSaveLinkUseCase.Param(
            linkUrl = state.url,
            categoryId = if (state.categoryId == 0.toLong()) null else state.categoryId,
          ),
        ).onSuccess {
          onSaveSuccess()
        }.onFailure {
          Log.e("error", it.toString())
        }
      }
    }
  }

  fun updateCategoryId(categoryId: Long?) = intent {
    reduce {
      state.copy(categoryId = categoryId)
    }
  }

  fun updateUrl(url: String) = intent {
    reduce {
      state.copy(url = url)
    }
  }

  private fun onSaveSuccess() = intent {
    postSideEffect(ShareSideEffect.ShareBottomSheetSideEffect.SaveSuccess)
  }
}
