package com.example.taskflow.domain.usecase


import com.example.taskflow.domain.model.User
import com.example.taskflow.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(
    private val repository: UserRepository
) {

    operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }
}