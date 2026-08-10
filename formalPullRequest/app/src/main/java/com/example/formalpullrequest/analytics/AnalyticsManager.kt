package com.example.formalpullrequest.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticsManager @Inject constructor(
    private val analytics: FirebaseAnalytics
) {

    fun logScreenView(
        screenName: String
    ) {
        analytics.logEvent(
            FirebaseAnalytics.Event.SCREEN_VIEW,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.SCREEN_NAME,
                    screenName
                )
            }
        )
    }

    fun logServiceSelected(
        serviceName: String
    ) {
        analytics.logEvent(
            "service_selected",
            Bundle().apply {
                putString(
                    "service_name",
                    serviceName
                )
            }
        )
    }

    fun logRefresh() {
        analytics.logEvent(
            "customer_data_refresh",
            null
        )
    }
}