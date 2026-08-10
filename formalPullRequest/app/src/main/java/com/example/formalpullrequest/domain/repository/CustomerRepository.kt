package com.example.formalpullrequest.domain.repository

import com.example.formalpullrequest.domain.model.Customer
import com.example.formalpullrequest.domain.model.CustomerService
import com.example.formalpullrequest.domain.model.UsageSummary
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    fun observeCustomer(): Flow<Customer?>

    fun observeServices(): Flow<List<CustomerService>>

    fun observeUsage(): Flow<UsageSummary?>

    suspend fun refreshCustomer(
        customerId: String
    )

    suspend fun refreshServices()

    suspend fun refreshUsage()
}