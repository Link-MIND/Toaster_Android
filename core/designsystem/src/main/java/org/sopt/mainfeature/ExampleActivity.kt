package org.sopt.mainfeature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ExampleActivity : AppCompatActivity() {
//  private val viewModel: ExampleViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_example)

//    viewModel.dummyState.flowWithLifecycle(lifecycle).onEach { state ->
//      when (state) {
//        is UiState.Loading -> {
//          TODO("LOADING")
//        }
//        is UiState.Success -> {
//          TODO("SUCCESS")
//        }
//        is UiState.Failure -> {
//          TODO("FAILURE")
//        }
//        is UiState.Empty -> {
//          TODO("EMPTY")
//        }
//      }
//    }
  }
}
