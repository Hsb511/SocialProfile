package com.team23.user.data.repositories

import com.team23.user.domain.models.UserModel

interface UserRepository {
    suspend fun getUserById(userId: String): UserModel?
}