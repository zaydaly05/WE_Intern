package com.example.animateddashboard.model

import kotlinx.serialization.Serializable

@Serializable
data class DashboardData(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)