package com.example.formalpullrequest.domain.model

data class UsageSummary(
    val dataUsedGb: Double,
    val dataLimitGb: Double,
    val voiceMinutesUsed: Int,
    val voiceMinutesLimit: Int,
    val smsUsed: Int,
    val smsLimit: Int
) {
    val dataPercentage: Float
        get() = percentage(dataUsedGb, dataLimitGb)

    val voicePercentage: Float
        get() = percentage(
            voiceMinutesUsed.toDouble(),
            voiceMinutesLimit.toDouble()
        )

    val smsPercentage: Float
        get() = percentage(
            smsUsed.toDouble(),
            smsLimit.toDouble()
        )

    private fun percentage(
        used: Double,
        limit: Double
    ): Float {
        return if (limit > 0) {
            (used / limit).toFloat().coerceIn(0f, 1f)
        } else {
            0f
        }
    }
}