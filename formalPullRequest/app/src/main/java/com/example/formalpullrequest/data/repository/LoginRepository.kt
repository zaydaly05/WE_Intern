package com.example.formalpullrequest.data.repository

import com.example.formalpullrequest.data.remote.ApiService
import com.example.formalpullrequest.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LoginRepository {

    override suspend fun login(
        phoneNumber: String,
        password: Int
    ): String? {

        val customers = apiService.getCustomers()

        val customer = customers.firstOrNull { customer ->

            customer.phoneNumber == phoneNumber &&
                    customer.password == password
        }

        return customer?.id
    }
}