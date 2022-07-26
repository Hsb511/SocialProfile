package com.team23.user.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.team23.user.R
import com.team23.user.domain.usecases.GetUserUseCase
import com.team23.user.ui.viewobjects.UserVO
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel @AssistedInject constructor(
    @Assisted private val userId: String?,
    private val getUserUseCase: GetUserUseCase
): ViewModel() {
    val user = mutableStateOf<UserVO?>(null)
    val error = mutableStateOf(-1)

    init {
        if (userId != null) {
            viewModelScope.launch(Dispatchers.IO) {
                val userVO = getUserUseCase.execute(userId)
                if (userVO != null) {
                    user.value = userVO
                } else {
                    error.value = R.string.user_profile_error_data
                }
            }
        } else {
            error.value = R.string.user_profile_error_userid
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(userId: String?): UserViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            userId: String?
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return assistedFactory.create(userId) as T
            }
        }
    }

}