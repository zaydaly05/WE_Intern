package com.example.formalpullrequest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustomerEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val accountNumber: String,
    val planName: String,
    val planPrice: Double,
    val dataUsedGb: Double,
    val dataLimitGb: Double
)