package com.example.animateddashboard.data.remote

import com.example.animateddashboard.model.DashboardData
import retrofit2.http.GET

interface DashboardApi {

    @GET("posts/1")
    suspend fun getPost(): DashboardData
}