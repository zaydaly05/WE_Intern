package com.example.pagingdemo.data.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitInstance {

    private const val BASE_URL =
        "https://dummyjson.com/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

    private val client =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    val api: ProductApi by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
            .create(ProductApi::class.java)
    }
}