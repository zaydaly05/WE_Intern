package com.example.pagingdemo.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pagingdemo.data.local.AppDatabase
import com.example.pagingdemo.data.local.ProductEntity
import com.example.pagingdemo.data.local.RemoteKeys
import com.example.pagingdemo.data.remote.ProductApi
import com.example.pagingdemo.data.toEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ProductRemoteMediator(
    private val database: AppDatabase,
    private val api: ProductApi
) : RemoteMediator<Int, ProductEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>
    ): MediatorResult {

        val limit = state.config.pageSize

        val skip = when (loadType) {

            LoadType.REFRESH -> {
                0
            }

            LoadType.PREPEND -> {

                val remoteKeys =
                    getRemoteKeyForFirstItem(state)

                val prevKey = remoteKeys?.prevKey

                if (prevKey == null) {
                    return MediatorResult.Success(
                        endOfPaginationReached =
                            remoteKeys != null
                    )
                }

                prevKey
            }

            LoadType.APPEND -> {

                val remoteKeys =
                    getRemoteKeyForLastItem(state)

                val nextKey = remoteKeys?.nextKey

                if (nextKey == null) {
                    return MediatorResult.Success(
                        endOfPaginationReached =
                            remoteKeys != null
                    )
                }

                nextKey
            }
        }

        return try {

            val response = api.getProducts(
                limit = limit,
                skip = skip
            )

            val products = response.products

            val endOfPaginationReached =
                products.isEmpty() ||
                        skip + products.size >= response.total

            database.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao()
                        .clearRemoteKeys()

                    database.productDao()
                        .clearAll()
                }

                val prevKey =
                    if (skip == 0) {
                        null
                    } else {
                        maxOf(skip - limit, 0)
                    }

                val nextKey =
                    if (endOfPaginationReached) {
                        null
                    } else {
                        skip + limit
                    }

                val keys = products.map { product ->

                    RemoteKeys(
                        productId = product.id,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }

                database.remoteKeysDao()
                    .insertAll(keys)

                database.productDao()
                    .insertAll(
                        products.map { it.toEntity() }
                    )
            }

            MediatorResult.Success(
                endOfPaginationReached =
                    endOfPaginationReached
            )

        } catch (e: IOException) {

            MediatorResult.Error(e)

        } catch (e: HttpException) {

            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ProductEntity>
    ): RemoteKeys? {

        return state.lastItemOrNull()?.let { product ->

            database.remoteKeysDao()
                .remoteKeysProductId(product.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ProductEntity>
    ): RemoteKeys? {

        return state.firstItemOrNull()?.let { product ->

            database.remoteKeysDao()
                .remoteKeysProductId(product.id)
        }
    }
}