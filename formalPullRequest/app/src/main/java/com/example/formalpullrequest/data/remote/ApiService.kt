package com.example.formalpullrequest.data.remote

import com.example.formalpullrequest.data.remote.dto.CustomerDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("customer")
    suspend fun getCustomers(): List<CustomerDto>

    @GET("customer/{customerId}")
    suspend fun getCustomer(
        @Path("customerId") customerId: String
    ): CustomerDto
}