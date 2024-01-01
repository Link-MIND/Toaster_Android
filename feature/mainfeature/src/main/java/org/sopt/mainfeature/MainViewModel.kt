package org.sopt.mainfeature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.auth.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val authRepository: AuthRepository
) : ViewModel() {

}
