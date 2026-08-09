package com.example.nativenavigation.ui.details

import androidx.lifecycle.ViewModel
import com.example.nativenavigation.ui.feed.Post

class DetailsViewModel : ViewModel() {

    private val posts = listOf(
        Post(1, "Jetpack Compose"),
        Post(2, "Navigation Compose"),
        Post(3, "StateFlow"),
        Post(4, "MVVM"),
        Post(5, "ViewModel")
    )

    fun getPost(id: Int): Post {
        return posts.first { it.id == id }
    }
}