package com.example.taskflow

import com.example.taskflow.domain.model.User
import com.example.taskflow.domain.repository.UserRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeUserRepository : UserRepository {

    val usersFlow = MutableSharedFlow<List<User>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun getUsers(): Flow<List<User>> {
        return usersFlow
    }
}