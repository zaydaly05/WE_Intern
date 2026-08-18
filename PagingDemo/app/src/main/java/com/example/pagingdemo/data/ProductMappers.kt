package com.example.pagingdemo.data

import com.example.pagingdemo.data.local.ProductEntity
import com.example.pagingdemo.data.remote.ProductDto

fun ProductDto.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        thumbnail = thumbnail
    )
}