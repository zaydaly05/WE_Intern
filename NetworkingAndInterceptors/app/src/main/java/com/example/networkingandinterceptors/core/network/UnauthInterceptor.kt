package com.example.networkingandinterceptors.core.network

import okhttp3.Interceptor
import okhttp3.Response

class UnauthInterceptor : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {

        val response = chain.proceed(chain.request())

        if (response.code == 401) {
            // Handle unauthorized globally
            // Example:
            // clear token
            // trigger logout
        }

        return response
    }
}