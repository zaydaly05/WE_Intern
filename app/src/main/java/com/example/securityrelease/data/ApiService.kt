package com.example.securityrelease.data

import com.example.securityrelease.data.model.Post
import com.example.securityrelease.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("posts/1")
    suspend fun getPost(): Post

    @GET("users/1")
    suspend fun getUser(): User
}