package com.example.taskflow.domain.repository


import com.example.taskflow.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
}