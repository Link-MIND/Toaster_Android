package org.sopt.mainfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import org.sopt.ui.view.UiState

@AndroidEntryPoint
class ExampleActivity : AppCompatActivity() {
  private val viewModel : ExampleViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_example)

    viewModel.dummyState.flowWithLifecycle(lifecycle).onEach { state ->
      when(state) {
        is UiState.Loading -> {
          TODO("LOADING")
        }
        is UiState.Success -> {
          TODO("SUCCESS")
        }
        is UiState.Failure -> {
          TODO("FAILURE")
        }
        is UiState.Empty -> {
          TODO("EMPTY")
        }
      }
    }
  }
}
