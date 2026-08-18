package com.capstone.feature.profile.data

import retrofit2.http.GET

interface ProfileApi {
    @GET("profile")
    suspend fun getProfile(): ProfileDto
}
