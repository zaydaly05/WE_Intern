package com.example.nativenavigation.ui.feed

import androidx.lifecycle.ViewModel

class FeedViewModel : ViewModel() {

    val posts = listOf(
        Post(1, "Jetpack Compose"),
        Post(2, "Navigation Compose"),
        Post(3, "StateFlow"),
        Post(4, "MVVM"),
        Post(5, "ViewModel")
    )
}