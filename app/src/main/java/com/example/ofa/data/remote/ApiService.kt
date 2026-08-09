package com.example.ofa.data.remote


import com.example.ofa.data.local.UserEntity
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UserEntity>

}