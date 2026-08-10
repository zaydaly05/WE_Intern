package com.example.formalpullrequest.data.session

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private var customerId: String? = null

    fun login(customerId: String) {
        this.customerId = customerId
    }

    fun getCustomerId(): String? {
        return customerId
    }

    fun logout() {
        customerId = null
    }

    fun isLoggedIn(): Boolean {
        return customerId != null
    }
}