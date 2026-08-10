package com.example.formalpullrequest.presentation.login

data class LoginUiState(
    val phoneNumber: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val customerId: String? = null,
    val errorMessage: String? = null
)