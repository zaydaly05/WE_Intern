package com.example.coroutinesandrooms.ui.theme
import com.example.coroutinesandrooms.viewmodel.PostViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PostScreen(
    viewModel: PostViewModel = viewModel()
) {
    val posts by viewModel.posts.collectAsState()

    LazyColumn {
        items(posts) { post ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = post.body,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}