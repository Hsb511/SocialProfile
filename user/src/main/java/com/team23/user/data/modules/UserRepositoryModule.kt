package com.team23.user.data.modules

import com.team23.user.data.repositories.UserRepository
import com.team23.user.data.repositories.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {
    @Binds
    abstract fun bindUserDataSource(userDataRepository: UserRepositoryImpl): UserRepository
}