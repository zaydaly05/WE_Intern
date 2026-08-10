package com.example.formalpullrequest.data.remote

/**
 * Retrofit configuration is provided through NetworkModule.
 *
 * Keeping Retrofit construction inside the Hilt module gives us:
 * - a single Retrofit instance
 * - centralized OkHttp configuration
 * - easier testing
 * - dependency injection throughout the application
 */
object RetrofitClient