package com.example.formalpullrequest.data.local.entities

import com.example.formalpullrequest.domain.model.Customer

fun CustomerEntity.toDomain(): Customer {
    return Customer(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        accountNumber = accountNumber,
        planName = planName,
        planPrice = planPrice,
        dataUsedGb = dataUsedGb,
        dataLimitGb = dataLimitGb
    )
}

fun Customer.toEntity(): CustomerEntity {
    return CustomerEntity(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        accountNumber = accountNumber,
        planName = planName,
        planPrice = planPrice,
        dataUsedGb = dataUsedGb,
        dataLimitGb = dataLimitGb
    )
}