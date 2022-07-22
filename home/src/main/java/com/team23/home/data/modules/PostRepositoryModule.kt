package com.team23.home.data.modules

import com.team23.home.data.repositories.PostRepository
import com.team23.home.data.repositories.PostRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PostRepositoryModule {

    @Binds
    abstract fun bindPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository
}