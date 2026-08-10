package com.example.formalpullrequest.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.formalpullrequest.data.local.entities.CustomerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers LIMIT 1")
    fun observeCustomer(): Flow<CustomerEntity?>

    @Query("SELECT * FROM customers LIMIT 1")
    suspend fun getCustomer(): CustomerEntity?

    @Upsert
    suspend fun upsertCustomer(customer: CustomerEntity)

    @Query("DELETE FROM customers")
    suspend fun deleteAll()
}