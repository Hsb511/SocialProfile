package com.team23.post.data.repositories

import com.team23.post.domain.models.PostModel

interface PostRepository {
    suspend fun getPostById(postId: String): PostModel?
}