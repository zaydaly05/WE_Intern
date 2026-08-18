package com.example.pagingdemo.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val products: List<ProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)