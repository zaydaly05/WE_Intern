package com.example.nativenavigation.ui.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FeedScreen(
    onPostClick: (Int) -> Unit,
    viewModel: FeedViewModel = viewModel()
) {

    LazyColumn {

        items(viewModel.posts) { post ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        onPostClick(post.id)
                    }
            ) {

                Text(
                    text = post.title,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}