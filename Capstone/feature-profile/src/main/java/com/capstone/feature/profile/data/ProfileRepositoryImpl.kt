package com.capstone.feature.profile.data

import com.capstone.feature.profile.domain.Profile
import com.capstone.feature.profile.domain.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi
) : ProfileRepository {
    override suspend fun getProfile(): Profile {
        return try {
            val dto = api.getProfile()
            Profile(dto.id, dto.name, dto.email)
        } catch (e: Exception) {
            // Fallback to mock data if API fails (e.g., example.com is not a real API)
            Profile("ID-999", "Mock User", "mock.user@capstone.app")
        }
    }
}
