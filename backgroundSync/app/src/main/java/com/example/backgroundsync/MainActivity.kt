package com.example.backgroundsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.backgroundsync.core.database.AppDatabase
import com.example.backgroundsync.core.network.RetrofitClient
import com.example.backgroundsync.data.PostRepository
import com.example.backgroundsync.feature.posts.PostsScreen
import com.example.backgroundsync.feature.posts.PostsViewModel
import com.example.backgroundsync.feature.posts.PostsViewModelFactory
import com.example.backgroundsync.worker.WorkManagerScheduler

class MainActivity : ComponentActivity() {

    private val viewModel: PostsViewModel by viewModels {

        val database = AppDatabase.getInstance(applicationContext)

        val repository = PostRepository(
            apiService = RetrofitClient.apiService,
            postDao = database.postDao()
        )

        PostsViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WorkManagerScheduler.scheduleDailySync(this)

        setContent {
            PostsScreen(
                viewModel = viewModel
            )
        }
    }
}