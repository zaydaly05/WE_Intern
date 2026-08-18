package com.example.animateddashboard.ui

import com.example.animateddashboard.model.DashboardData

sealed interface UiState {

    data object Loading : UiState

    data class Success(
        val data: DashboardData
    ) : UiState

    data class Error(
        val message: String
    ) : UiState
}