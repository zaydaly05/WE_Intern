package com.capstone.feature.profile.domain

import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Profile = repository.getProfile()
}
