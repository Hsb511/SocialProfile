package com.team23.home.data.repositories

import com.team23.home.domain.models.PostModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPosts(): List<PostModel>
}