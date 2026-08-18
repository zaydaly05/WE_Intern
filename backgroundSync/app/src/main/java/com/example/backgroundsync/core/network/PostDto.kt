package com.example.backgroundsync.core.network

import com.example.backgroundsync.core.database.PostEntity

data class PostDto(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

fun PostDto.toEntity(): PostEntity {
    return PostEntity(
        id = id,
        userId = userId,
        title = title,
        body = body
    )
}