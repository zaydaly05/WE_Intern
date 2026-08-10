package com.example.formalpullrequest.data.repository

import com.example.formalpullrequest.data.local.CustomerDao
import com.example.formalpullrequest.data.local.entities.toDomain
import com.example.formalpullrequest.data.local.entities.toEntity
import com.example.formalpullrequest.data.remote.ApiService
import com.example.formalpullrequest.domain.model.Customer
import com.example.formalpullrequest.domain.model.CustomerService
import com.example.formalpullrequest.domain.model.ServiceIcon
import com.example.formalpullrequest.domain.model.UsageSummary
import com.example.formalpullrequest.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val customerDao: CustomerDao,
    private val apiService: ApiService
) : CustomerRepository {

    override fun observeCustomer(): Flow<Customer?> {
        return customerDao
            .observeCustomer()
            .map { entity ->
                entity?.toDomain()
            }
    }

    override fun observeServices(): Flow<List<CustomerService>> {
        return flowOf(
            listOf(
                CustomerService(
                    id = "mobile",
                    name = "Mobile",
                    description = "Your mobile line and plan",
                    isActive = true,
                    iconType = ServiceIcon.MOBILE
                ),
                CustomerService(
                    id = "internet",
                    name = "Home Internet",
                    description = "Home broadband service",
                    isActive = true,
                    iconType = ServiceIcon.INTERNET
                ),
                CustomerService(
                    id = "landline",
                    name = "Landline",
                    description = "Your fixed telephone service",
                    isActive = true,
                    iconType = ServiceIcon.LANDLINE
                )
            )
        )
    }

    override fun observeUsage(): Flow<UsageSummary?> {
        return observeCustomer().map { customer ->
            customer?.let {
                UsageSummary(
                    dataUsedGb = it.dataUsedGb,
                    dataLimitGb = it.dataLimitGb,
                    voiceMinutesUsed = 320,
                    voiceMinutesLimit = 1000,
                    smsUsed = 85,
                    smsLimit = 500
                )
            }
        }
    }

    override suspend fun refreshCustomer(customerId: String) {

        val response = apiService.getCustomer(customerId)

        val customer = Customer(
            id = response.id.toInt(),
            name = response.name,
            phoneNumber = response.phoneNumber,
            accountNumber = response.accountNumber,
            planName = response.planName,
            planPrice = response.planPrice,
            dataUsedGb = response.dataUsedGb,
            dataLimitGb = response.dataLimitGb
        )

        customerDao.deleteAll()

        customerDao.upsertCustomer(
            customer.toEntity()
        )
    }

    override suspend fun refreshServices() {
        // Services will be loaded from the backend later.
    }

    override suspend fun refreshUsage() {
        // Usage will be loaded from the backend later.
    }
}