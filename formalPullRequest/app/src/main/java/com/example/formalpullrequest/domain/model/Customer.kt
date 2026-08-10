package com.example.formalpullrequest.domain.model

data class Customer(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val accountNumber: String,
    val planName: String,
    val planPrice: Double,
    val dataUsedGb: Double,
    val dataLimitGb: Double
) {
    val dataUsagePercentage: Float
        get() {
            if (dataLimitGb <= 0) return 0f

            return (dataUsedGb / dataLimitGb)
                .coerceIn(0.0, 1.0)
                .toFloat()
        }
}