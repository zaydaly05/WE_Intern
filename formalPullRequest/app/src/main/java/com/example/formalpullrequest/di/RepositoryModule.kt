package com.example.formalpullrequest.di

import com.example.formalpullrequest.data.repository.CustomerRepositoryImpl
import com.example.formalpullrequest.domain.repository.CustomerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCustomerRepository(
        repository: CustomerRepositoryImpl
    ): CustomerRepository
}