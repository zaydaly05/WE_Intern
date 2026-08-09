package com.example.nativenavigation.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailsScreen(
    postId: Int,
    viewModel: DetailsViewModel = viewModel()
) {

    val post = viewModel.getPost(postId)

    Column(
        modifier = Modifier.padding(20.dp)
    ) {

        Text(
            text = "Post ID: ${post.id}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = post.title,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}