package com.capstone.feature.profile.domain

interface ProfileRepository {
    suspend fun getProfile(): Profile
}
