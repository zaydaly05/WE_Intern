package com.example.backgroundsync.feature.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostsScreen(
    viewModel: PostsViewModel
) {

    val posts by viewModel.posts.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Background Sync",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Room + WorkManager + Retrofit",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )

        Button(
            onClick = {
                viewModel.syncNow()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {

            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Sync"
            )

            Text(
                text = "Sync Now",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Text(
            text = "Stored posts: ${posts.size}",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(
                items = posts,
                key = { it.id }
            ) { post ->

                PostCard(post)
            }
        }
    }
}

@Composable
private fun PostCard(
    post: com.example.backgroundsync.core.database.PostEntity
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "Post #${post.id}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}