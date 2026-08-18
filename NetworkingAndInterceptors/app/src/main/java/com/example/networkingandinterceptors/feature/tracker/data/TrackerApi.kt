package com.example.networkingandinterceptors.feature.tracker.data

import retrofit2.http.GET
import retrofit2.http.Query

interface TrackerApi {

    @GET("posts")
    suspend fun getUserData(
        @Query("userId") userId: Int
    ): List<TrackerItem>
}