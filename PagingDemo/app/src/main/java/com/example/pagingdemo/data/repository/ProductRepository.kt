package com.example.pagingdemo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pagingdemo.data.local.AppDatabase
import com.example.pagingdemo.data.local.ProductEntity
import com.example.pagingdemo.data.mediator.ProductRemoteMediator
import com.example.pagingdemo.data.remote.ProductApi
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val database: AppDatabase,
    private val api: ProductApi
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getProducts(): Flow<PagingData<ProductEntity>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),

            remoteMediator = ProductRemoteMediator(
                database = database,
                api = api
            ),

            pagingSourceFactory = {
                database.productDao().pagingSource()
            }
        ).flow
    }
}