package com.example.networkingandinterceptors.feature.tracker.data

import javax.inject.Inject

class TrackerRepository @Inject constructor(
    private val api: TrackerApi
) {

    suspend fun getUserData(userId: Int): List<TrackerItem> {
        return api.getUserData(userId)
    }
}