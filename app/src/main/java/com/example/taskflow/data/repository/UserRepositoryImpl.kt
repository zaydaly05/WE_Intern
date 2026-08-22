package com.example.taskflow.data.repository

import com.example.taskflow.domain.model.User
import com.example.taskflow.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
class UserRepositoryImpl : UserRepository {

    override fun getUsers(): Flow<List<User>> = flow {

        emit(
            listOf(
                User(1, "Zaid"),
                User(2, "Ahmed"),
                User(3, "Mohamed")
            )
        )
    }
}