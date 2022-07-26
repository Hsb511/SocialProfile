package com.team23.user.domain.usecases

import androidx.compose.ui.graphics.Color
import com.team23.user.R
import com.team23.user.data.repositories.UserRepository
import com.team23.user.ui.viewobjects.UserVO
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(userId: String): UserVO? = userRepository.getUserById(userId)?.let {
        UserVO(
            name = it.name,
            picture = it.picture,
            // TODO CHANGE THAT
            backgroundColor = Color.Blue,
            genderResId = if (it.gender == "female") { R.drawable.ic_female } else { R.drawable.ic_male },
            dateOfBirth = it.dateOfBirth,
            contactData = it.contactData
        )
    }
}