package com.team23.user.domain.usecases

import com.team23.user.data.repositories.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(userId: String) = userRepository.getUserById(userId)
}