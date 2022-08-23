package com.team23.post.domain.usecases

import com.team23.post.data.repositories.PostRepository
import com.team23.post.domain.models.PostModel
import javax.inject.Inject

class GetPostDataUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun execute(postId: String): PostModel? = postRepository.getPostById(postId)
}