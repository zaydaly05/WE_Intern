package com.example.formalpullrequest.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CustomerDto(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val accountNumber: String,
    val planName: String,
    val planPrice: Double,
    val dataUsedGb: Double,
    val dataLimitGb: Double,
    val password: Int
)