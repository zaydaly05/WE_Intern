package com.example.formalpullrequest.di

import com.example.formalpullrequest.data.repository.LoginRepositoryImpl
import com.example.formalpullrequest.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {

    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        implementation: LoginRepositoryImpl
    ): LoginRepository
}