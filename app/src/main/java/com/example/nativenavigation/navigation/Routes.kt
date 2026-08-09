package com.example.nativenavigation.navigation

import kotlinx.serialization.Serializable

@Serializable
object Feed

@Serializable
data class Details(
    val postId: Int
)