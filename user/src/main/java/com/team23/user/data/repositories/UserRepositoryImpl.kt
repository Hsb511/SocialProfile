package com.team23.user.data.repositories

import com.team23.api.UserService
import com.team23.user.data.mappers.toModel
import com.team23.user.domain.models.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
): UserRepository {
    override suspend fun getUserById(userId: String): UserModel? {
        val response = withContext(Dispatchers.IO) { userService.getUserData(userId) }
        return if (response.isSuccessful) {
            response.body()?.toModel(Dispatchers.IO)
        } else {
            null
        }
    }
}