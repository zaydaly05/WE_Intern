package com.example.taskflow.presentation


import com.example.taskflow.domain.model.User

sealed interface UserUiState {

    data object Loading : UserUiState

    data class Success(
        val users: List<User>
    ) : UserUiState

    data class Error(
        val message: String
    ) : UserUiState
}