package com.example.coroutinesandrooms.network

import com.example.coroutinesandrooms.model.PostsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): PostsResponse

}