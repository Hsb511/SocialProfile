package com.team23.user.domain.usecases

import com.team23.core.extensions.toBirthDate
import com.team23.user.R
import com.team23.user.data.repositories.UserRepository
import com.team23.user.ui.viewobjects.UserVO
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(userId: String): UserVO? = userRepository.getUserById(userId)?.let {
        val age = it.dateOfBirth.until(LocalDateTime.now(), ChronoUnit.YEARS)
        UserVO(
            name = it.name,
            picture = it.picture,
            backgroundResId = when {
                age < 30 -> R.drawable.picture_beach
                age < 60 -> R.drawable.picture_forest
                else -> R.drawable.picture_mountain
            },
            genderResId = if (it.gender == "female") {
                R.drawable.ic_female
            } else {
                R.drawable.ic_male
            },
            dateOfBirth = it.dateOfBirth.toBirthDate(),
            contactData = it.contactData
        )
    }
}