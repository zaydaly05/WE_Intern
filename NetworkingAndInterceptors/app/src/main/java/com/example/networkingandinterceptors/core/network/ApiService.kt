package com.example.networkingandinterceptors.core.network

import com.example.networkingandinterceptors.feature.tracker.data.TrackerItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getUserData(
        @Query("userId") userId: Int
    ): List<TrackerItem>
}