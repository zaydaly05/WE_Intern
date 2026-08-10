package com.example.formalpullrequest.domain.repository

interface LoginRepository {

    suspend fun login(
        phoneNumber: String,
        password: Int
    ): String?
}