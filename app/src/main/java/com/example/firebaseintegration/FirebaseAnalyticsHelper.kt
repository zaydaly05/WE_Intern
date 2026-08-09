package com.example.firebaseintegration

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsHelper(context: Context) {

    private val analytics = FirebaseAnalytics.getInstance(context)

    fun logButtonClicked(buttonName: String) {
        val bundle = Bundle().apply {
            putString("button_name", buttonName)
        }

        analytics.logEvent("button_clicked", bundle)
    }

    fun logScreenOpened(screenName: String) {
        val bundle = Bundle().apply {
            putString("screen_name", screenName)
        }

        analytics.logEvent("screen_opened", bundle)
    }
}