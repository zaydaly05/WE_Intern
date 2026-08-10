package com.example.formalpullrequest.presentation.home

import com.example.formalpullrequest.domain.model.Customer

data class HomeUiState(
    val customer: Customer? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null
)