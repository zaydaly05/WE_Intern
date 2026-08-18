package com.example.pagingdemo.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): ProductResponse
}