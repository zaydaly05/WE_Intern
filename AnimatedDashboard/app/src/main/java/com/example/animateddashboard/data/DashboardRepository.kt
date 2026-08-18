package com.example.animateddashboard.data

import com.example.animateddashboard.data.remote.DashboardApi
import com.example.animateddashboard.model.DashboardData

class DashboardRepository(
    private val api: DashboardApi
) {

    suspend fun fetchDashboardData(): DashboardData {
        return api.getPost()
    }
}