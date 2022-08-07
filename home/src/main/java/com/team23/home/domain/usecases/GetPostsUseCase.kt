package com.team23.home.domain.usecases

import com.team23.home.data.repositories.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    fun execute() = postRepository.getPosts()
}